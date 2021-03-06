package org.team10424102.whisky.models;

import android.databinding.BaseObservable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.StringRes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import org.team10424102.whisky.App;
import org.team10424102.whisky.R;
import org.team10424102.whisky.models.enums.EActivityType;

import java.util.Date;
import java.util.List;

/**
 * Created by yy on 10/30/15.
 */
public class Activity extends BaseObservable implements Parcelable {
    private Long id;
    private String title;
    private String content;
    private String location;
    private Date startTime;
    private Date endTime;
    private Date registrationDeadline;
    private Date creationTime;
    private LazyImage cover;
    private List<LazyImage> photos;
    private EActivityType type;
    private User promoter;
    private Game game;
    private long groupId;
    private int commentsCount;
    private int likesCount;


    /////////////////////////////////////////////////////////////////
    //                                                             //
    //                    ~~~~~~~~~~~~~~~~~                        //
    //                        GET & SET                            //
    //                    =================                        //
    //                                                             //
    /////////////////////////////////////////////////////////////////


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    public Date getRegistrationDeadline() {
        return registrationDeadline;
    }

    public void setRegistrationDeadline(Date registrationDeadline) {
        this.registrationDeadline = registrationDeadline;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public LazyImage getCover() {
        return cover;
    }

    public void setCover(LazyImage cover) {
        this.cover = cover;
    }

    public List<LazyImage> getPhotos() {
        return photos;
    }

    public void setPhotos(List<LazyImage> photos) {
        this.photos = photos;
    }

    public EActivityType getType() {
        return type;
    }

    public void setType(EActivityType type) {
        this.type = type;
    }

    public User getPromoter() {
        return promoter;
    }

    public void setPromoter(User promoter) {
        this.promoter = promoter;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @JsonProperty("group")
    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    @JsonProperty("comments")
    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    @JsonProperty("likes")
    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    /////////////////////////////////////////////////////////////////
    //                                                             //
    //                    ~~~~~~~~~~~~~~~~~                        //
    //                         Others                              //
    //                    =================                        //
    //                                                             //
    /////////////////////////////////////////////////////////////////

    @StringRes
    public int getStatus() {
        Date now = new Date();
        if (now.before(registrationDeadline)) return R.string.activity_status_ready;
        else if (now.before(startTime)) return R.string.activity_status_signup;
        else if (now.before(endTime)) return R.string.activity_status_running;
        return R.string.activity_status_ended;
    }

    public int getFocusedMemberCount() {
        return 0;
    }

    @JsonSetter("game")
    public void setGameByIdentifier(String identifier) {
        this.game = App.getInstance().getGameManager().getGame(identifier);
    }


    /////////////////////////////////////////////////////////////////
    //                                                             //
    //                    ~~~~~~~~~~~~~~~~~                        //
    //                     Object Override                         //
    //                    =================                        //
    //                                                             //
    /////////////////////////////////////////////////////////////////


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Activity activity = (Activity) o;

        return !(id != null ? !id.equals(activity.id) : activity.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return String.format("活动 (编号 = %d, 标题 = %s)", getId(), getTitle());
    }


    /////////////////////////////////////////////////////////////////
    //                                                             //
    //                    ~~~~~~~~~~~~~~~~~                        //
    //                        Parcelable                           //
    //                    =================                        //
    //                                                             //
    /////////////////////////////////////////////////////////////////

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeString(this.location);
        dest.writeLong(startTime != null ? startTime.getTime() : -1);
        dest.writeLong(endTime != null ? endTime.getTime() : -1);
        dest.writeLong(registrationDeadline != null ? registrationDeadline.getTime() : -1);
        dest.writeLong(creationTime != null ? creationTime.getTime() : -1);
        dest.writeParcelable(this.cover, 0);
        dest.writeTypedList(photos);
        dest.writeInt(this.type == null ? -1 : this.type.ordinal());
        dest.writeParcelable(this.promoter, 0);
        dest.writeParcelable(this.game, 0);
        dest.writeLong(this.groupId);
        dest.writeInt(this.commentsCount);
        dest.writeInt(this.likesCount);
    }

    public Activity() {
    }

    protected Activity(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.title = in.readString();
        this.content = in.readString();
        this.location = in.readString();
        long tmpStartTime = in.readLong();
        this.startTime = tmpStartTime == -1 ? null : new Date(tmpStartTime);
        long tmpEndTime = in.readLong();
        this.endTime = tmpEndTime == -1 ? null : new Date(tmpEndTime);
        long tmpRegistrationDeadline = in.readLong();
        this.registrationDeadline = tmpRegistrationDeadline == -1 ? null : new Date(tmpRegistrationDeadline);
        long tmpCreationTime = in.readLong();
        this.creationTime = tmpCreationTime == -1 ? null : new Date(tmpCreationTime);
        this.cover = in.readParcelable(LazyImage.class.getClassLoader());
        this.photos = in.createTypedArrayList(LazyImage.CREATOR);
        int tmpType = in.readInt();
        this.type = tmpType == -1 ? null : EActivityType.values()[tmpType];
        this.promoter = in.readParcelable(User.class.getClassLoader());
        this.game = in.readParcelable(Game.class.getClassLoader());
        this.groupId = in.readLong();
        this.commentsCount = in.readInt();
        this.likesCount = in.readInt();
    }

    public static final Creator<Activity> CREATOR = new Creator<Activity>() {
        public Activity createFromParcel(Parcel source) {
            return new Activity(source);
        }

        public Activity[] newArray(int size) {
            return new Activity[size];
        }
    };
}
