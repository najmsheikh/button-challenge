package me.najmsheikh.buttonchallenge.models;

/**
 * Created by Najm Sheikh <hello@najmsheikh.me> on 3/5/18.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class User implements Parcelable {

    private final String CANDIDATE = "npcompete";

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("candidate")
    @Expose
    private String candidate;
    public final static Parcelable.Creator<User> CREATOR = new Creator<User>() {


        @SuppressWarnings({
                "unchecked"
        })
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return (new User[size]);
        }

    };

    protected User(Parcel in) {
        this.id = ((Long) in.readValue((Long.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.candidate = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public User() {
    }

    /**
     * @param name  The given name of the User
     * @param email The provided email of the User
     */
    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.candidate = CANDIDATE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User withId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User withName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public User withCandidate(String candidate) {
        this.candidate = candidate;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("email", email)
                .append("candidate", candidate)
                .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(email)
                .append(name)
                .append(candidate)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof User)) {
            return false;
        }
        User rhs = ((User) other);
        return new EqualsBuilder()
                .append(id, rhs.id)
                .append(email, rhs.email)
                .append(name, rhs.name)
                .append(candidate, rhs.candidate)
                .isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(email);
        dest.writeValue(candidate);
    }

    public int describeContents() {
        return 0;
    }

}