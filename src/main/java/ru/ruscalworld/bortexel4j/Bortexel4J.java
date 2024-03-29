package ru.ruscalworld.bortexel4j;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jetbrains.annotations.Nullable;
import ru.ruscalworld.bortexel4j.auth.credentials.ApiCredentials;
import ru.ruscalworld.bortexel4j.auth.credentials.AuthorizationKey;
import ru.ruscalworld.bortexel4j.auth.credentials.BearerToken;
import ru.ruscalworld.bortexel4j.core.Action;
import ru.ruscalworld.bortexel4j.core.PaginatedListAction;
import ru.ruscalworld.bortexel4j.exceptions.LoginException;
import ru.ruscalworld.bortexel4j.listening.BroadcastingServer;
import ru.ruscalworld.bortexel4j.models.account.Account;
import ru.ruscalworld.bortexel4j.models.authorization.AuthCheck;
import ru.ruscalworld.bortexel4j.models.ban.Ban;
import ru.ruscalworld.bortexel4j.models.city.City;
import ru.ruscalworld.bortexel4j.models.economy.Item;
import ru.ruscalworld.bortexel4j.models.economy.Report;
import ru.ruscalworld.bortexel4j.models.photo.Photo;
import ru.ruscalworld.bortexel4j.models.profile.Profile;
import ru.ruscalworld.bortexel4j.models.season.Season;
import ru.ruscalworld.bortexel4j.models.shop.Shop;
import ru.ruscalworld.bortexel4j.models.user.User;
import ru.ruscalworld.bortexel4j.models.warning.Warning;

import java.net.InetAddress;
import java.util.List;

public class Bortexel4J implements Client {
    public static final String DEFAULT_API_URL = "https://api.bortexel.net/v3";

    private final ApiCredentials credentials;
    private String owner;
    private int level;
    private OkHttpClient httpClient;
    private String apiUrl = DEFAULT_API_URL;

    private Bortexel4J(ApiCredentials credentials, String owner, int level, String apiUrl, OkHttpClient httpClient) {
        this.credentials = credentials;
        this.owner = owner;
        this.level = level;
        this.apiUrl = apiUrl;
        this.httpClient = httpClient;
    }

    private Bortexel4J(ApiCredentials credentials, String owner, int level, String apiUrl) {
        this.credentials = credentials;
        this.owner = owner;
        this.level = level;
        this.apiUrl = apiUrl;
    }

    @Deprecated
    public Bortexel4J(String token, String apiUrl, OkHttpClient httpClient) {
        this.credentials = new BearerToken(token);
        this.owner = null;
        this.level = 0;
        this.apiUrl = apiUrl;
        this.httpClient = httpClient;
    }

    public Bortexel4J(ApiCredentials credentials, String apiUrl, OkHttpClient httpClient) {
        this.credentials = credentials;
        this.owner = null;
        this.level = 0;
        this.apiUrl = apiUrl;
        this.httpClient = httpClient;
    }

    @Deprecated
    public Bortexel4J(String token) {
        this.credentials = new BearerToken(token);
        this.owner = null;
        this.level = 0;
    }

    public Bortexel4J(ApiCredentials credentials) {
        this.credentials = credentials;
        this.owner = null;
        this.level = 0;
    }

    public Bortexel4J() {
        this.credentials = null;
        this.owner = null;
        this.level = 0;
    }

    public static Bortexel4J anonymous() {
        return new Bortexel4J();
    }

    @Deprecated
    public static Bortexel4J login(String token) throws LoginException {
        return login(token, DEFAULT_API_URL, true, null);
    }

    public static Bortexel4J login(ApiCredentials credentials) throws LoginException {
        return login(credentials, DEFAULT_API_URL, true, null);
    }

    @Deprecated
    public static Bortexel4J login(String token, String apiUrl) {
        return login(token, apiUrl, true, null);
    }

    public static Bortexel4J login(ApiCredentials credentials, String apiUrl) {
        return login(credentials, apiUrl, true, null);
    }

    @Deprecated
    public static Bortexel4J login(String token, String apiUrl, OkHttpClient httpClient) {
        return login(new BearerToken(token), apiUrl, true, httpClient);
    }

    public static Bortexel4J login(ApiCredentials credentials, String apiUrl, OkHttpClient httpClient) {
        return login(credentials, apiUrl, true, httpClient);
    }

    @Deprecated
    public static Bortexel4J login(String token, String apiUrl, boolean checkAuth, OkHttpClient httpClient) throws LoginException {
        return login(new BearerToken(token), apiUrl, checkAuth, httpClient);
    }

    public static Bortexel4J login(ApiCredentials credentials, String apiUrl, boolean checkAuth, OkHttpClient httpClient) throws LoginException {
        Bortexel4J client = new Bortexel4J(credentials, apiUrl, httpClient);
        if (!checkAuth) return client;
        AuthCheck authorization = AuthCheck.checkToken(client).execute();
        if (!authorization.isAuthorized()) throw new LoginException();
        client.setLevel(authorization.getLevel());
        client.setOwner(authorization.getUsername());
        return client;
    }

    public static Bortexel4J login() {
        return login(AuthorizationKey.fromString(System.getenv("BORTEXEL_TOKEN")), DEFAULT_API_URL, false, null);
    }

    public Request.Builder getDefaultRequestBuilder() {
        Request.Builder builder = new Request.Builder();
        if (this.credentials != null) {
            builder = this.getCredentials().fillRequest(builder);
        }
        return builder;
    }

    public static OkHttpClient getDefaultHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        return builder.build();
    }

    public OkHttpClient getHttpClient() {
        if (this.httpClient == null) this.httpClient = getDefaultHttpClient();
        return this.httpClient;
    }

    public void setHttpClient(OkHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Call createCall(Request request) {
        return this.getHttpClient().newCall(request);
    }

    public BroadcastingServer getBroadcastingServer() {
        return this.getBroadcastingServer(null);
    }

    public BroadcastingServer getBroadcastingServer(@Nullable String url) {
        BroadcastingServer server = new BroadcastingServer(this.getHttpClient());
        server.setCredentials(this.getCredentials());
        if (url != null) server.setURL(url);
        return server;
    }

    public RuleClient getRuleClient() {
        return new RuleClient(this.getHttpClient());
    }

    public int getLevel() {
        return level;
    }

    public String getOwner() {
        return owner;
    }

    @Deprecated
    public String getToken() {
        if (this.getCredentials() == null) return null;
        return this.getCredentials().getAuthorizationKey();
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    private void setOwner(String owner) {
        this.owner = owner;
    }

    private void setLevel(int level) {
        this.level = level;
    }

    public Action<Void> ping() {
        return new Action<>("/", this);
    }

    public Action<Account> getAccountByID(int id) {
        return Account.getByID(id, this);
    }

    public Action<Account> getAccountByDiscordID(String id) {
        return Account.getByDiscordID(id, this);
    }

    public PaginatedListAction<Account> getAllAccounts() {
        return Account.getAll(this);
    }

    public Action<Ban> getBanByID(int id) {
        return Ban.getByID(id, this);
    }

    public Action<List<Ban>> getBanByAddress(InetAddress address) {
        return Ban.getByAddress(address, this);
    }

    public Action<List<Ban>> getBanByAddress(String address) {
        return Ban.getByAddress(address, this);
    }

    public Action<City> getCityByID(int id) {
        return City.getByID(id, this);
    }

    public PaginatedListAction<City> getAllCities() {
        return City.getAll(this);
    }

    public Action<Item> getItemByID(String id) {
        return Item.getByID(id, this);
    }

    public Action<List<Item.Category>> getAllItems() {
        return Item.getAll(this);
    }

    public Action<Report> getEconomyReportByID(int id) {
        return Report.getByID(id, this);
    }

    public Action<Photo> getPhotoByID(int id) {
        return Photo.getByID(id, this);
    }

    public PaginatedListAction<Photo> getAllPhotos() {
        return Photo.getAll(this);
    }

    public Action<Profile> getProfileByUserName(String userName) {
        return Profile.getByUserName(userName, this);
    }

    public Action<Season> getSeasonByID(int id) {
        return Season.getByID(id, this);
    }

    public Action<List<Season>> getAllSeasons() {
        return Season.getAll(this);
    }

    public Action<Shop> getShopByID(int id) {
        return Shop.getByID(id, this);
    }

    public PaginatedListAction<Shop> getAllShops() {
        return Shop.getAll(this);
    }

    public Action<User> getUserByID(int id) {
        return User.getByID(id, this);
    }

    public Action<User> getUserByName(String userName) {
        return User.getByUsername(userName, this);
    }

    public Action<Warning> getWarningByID(int id) {
        return Warning.getByID(id, this);
    }

    public PaginatedListAction<Warning> getAllWarnings() {
        return Warning.getAll(this);
    }

    public ApiCredentials getCredentials() {
        return credentials;
    }
}
