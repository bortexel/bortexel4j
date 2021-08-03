package ru.ruscalworld.bortexel4j.models.user;

import com.google.gson.annotations.SerializedName;
import ru.ruscalworld.bortexel4j.Bortexel4J;
import ru.ruscalworld.bortexel4j.core.Action;
import ru.ruscalworld.bortexel4j.models.account.Account;
import ru.ruscalworld.bortexel4j.models.ban.Ban;

import java.sql.Timestamp;
import java.util.List;

public class User {
    private final int id;
    private String username;

    @SerializedName("last_login")
    private int lastLogin;

    @SerializedName("valid_till")
    private Timestamp validTill;

    @SerializedName("active_till")
    private Timestamp activeTill;

    @SerializedName("allow_from")
    private int allowFrom;

    @SerializedName("allow_till")
    private int allowTill;

    @SerializedName("online_auth")
    private boolean onlineAuth;

    @SerializedName("deny_proxy")
    private boolean denyProxy;

    private boolean permanent;
    private boolean strange;

    @SerializedName("warn_power")
    private int warnPower;

    @SerializedName("warn_count")
    private int warnCount;

    @SerializedName("skin_system")
    private final String skinSystem;

    @SerializedName("skin_name")
    private final String skinName;

    public User(int id, String username, int lastLogin, Timestamp validTill, Timestamp activeTill, boolean permanent, boolean strange, int warnPower, int warnCount, String skinSystem, String skinName) {
        this.id = id;
        this.username = username;
        this.lastLogin = lastLogin;
        this.validTill = validTill;
        this.activeTill = activeTill;
        this.permanent = permanent;
        this.strange = strange;
        this.warnPower = warnPower;
        this.warnCount = warnCount;
        this.skinSystem = skinSystem;
        this.skinName = skinName;
    }

    public static Action<User> getByID(int id, Bortexel4J client) {
        Action<User> action = new Action<>("/users/" + id, client);
        action.setResponseType(User.class);
        return action;
    }

    public static Action<User> getByUsername(String username, Bortexel4J client) {
        Action<User> action = new Action<>("/players/" + username, client);
        action.setResponseType(User.class);
        return action;
    }

    public Action<UserSkin> getSkin(Bortexel4J client) {
        return UserSkin.getByUserID(this.getID(), client);
    }

    public Action<UserSkin> setSkin(String system, String name, Bortexel4J client) {
        return UserSkin.setByUserID(this.getID(), system, name, client);
    }

    public Action<UserSkin> resetSkin(Bortexel4J client) {
        return UserSkin.resetByUserID(this.getID(), client);
    }

    public Action<UserBans> getBans(Bortexel4J client) {
        return UserBans.getByUserID(this.getID(), client);
    }

    public Action<UserAccount> getAccount(Bortexel4J client) {
        return UserAccount.getByUserID(this.getID(), client);
    }

    public static class UserBans {
        private final User user;
        private final List<Ban> bans;

        public UserBans(User user, List<Ban> bans) {
            this.user = user;
            this.bans = bans;
        }

        public static Action<UserBans> getByUserID(int id, Bortexel4J client) {
            Action<UserBans> action = new Action<>("/users/" + id + "/bans", client);
            action.setResponseType(UserBans.class);
            return action;
        }

        public User getUser() {
            return user;
        }

        public List<Ban> getBans() {
            return bans;
        }
    }

    public static class UserAccount {
        private final User user;
        private final Account account;

        public UserAccount(User user, Account account) {
            this.user = user;
            this.account = account;
        }

        public static Action<UserAccount> getByUserID(int id, Bortexel4J client) {
            Action<UserAccount> action = new Action<>("/users/" + id + "/account", client);
            action.setResponseType(UserAccount.class);
            return action;
        }

        public User getUser() {
            return user;
        }

        public Account getAccount() {
            return account;
        }
    }

    public int getID() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(int lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Timestamp getValidTill() {
        return validTill;
    }

    public void setValidTill(Timestamp validTill) {
        this.validTill = validTill;
    }

    public Timestamp getActiveTill() {
        return activeTill;
    }

    public void setActiveTill(Timestamp activeTill) {
        this.activeTill = activeTill;
    }

    public boolean isPermanent() {
        return permanent;
    }

    public void setPermanent(boolean permanent) {
        this.permanent = permanent;
    }

    public boolean isStrange() {
        return strange;
    }

    public void setStrange(boolean strange) {
        this.strange = strange;
    }

    public int getWarnPower() {
        return warnPower;
    }

    public void setWarnPower(int warnPower) {
        this.warnPower = warnPower;
    }

    public int getWarnCount() {
        return warnCount;
    }

    public void setWarnCount(int warnCount) {
        this.warnCount = warnCount;
    }

    public String getSkinName() {
        return skinName;
    }

    public String getSkinSystem() {
        return skinSystem;
    }

    public boolean isOnlineAuthEnabled() {
        return onlineAuth;
    }

    public void setOnlineAuthEnabled(boolean onlineAuth) {
        this.onlineAuth = onlineAuth;
    }

    public int getAllowFrom() {
        return allowFrom;
    }

    public void setAllowFrom(int allowFrom) {
        this.allowFrom = allowFrom;
    }

    public int getAllowTill() {
        return allowTill;
    }

    public void setAllowTill(int allowTill) {
        this.allowTill = allowTill;
    }

    public boolean isProxyDenied() {
        return denyProxy;
    }

    public void setProxyDenied(boolean denyProxy) {
        this.denyProxy = denyProxy;
    }
}
