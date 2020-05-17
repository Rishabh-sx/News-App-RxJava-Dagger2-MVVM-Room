package com.newsapp.app.dataclass

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "article")
class Article : Parcelable {
    @SerializedName("author")
    @Expose
    var author: String?

    @SerializedName("title")
    @Expose
    var title: String?

    @SerializedName("description")
    @Expose
    var description: String?

    @PrimaryKey
    @SerializedName("url")
    @Expose
    var url: String

    @SerializedName("urlToImage")
    @Expose
    var urlToImage: String?

    @SerializedName("publishedAt")
    @Expose
    var publishedAt: String?

    @SerializedName("content")
    @Expose
    var content: String?

    constructor(
        author: String?,
        title: String?,
        description: String?,
        url: String,
        urlToImage: String?,
        publishedAt: String?,
        content: String?
    ) {
        this.author = author
        this.title = title
        this.description = description
        this.url = url
        this.urlToImage = urlToImage
        this.publishedAt = publishedAt
        this.content = content
    }

    protected constructor(`in`: Parcel) {
        author = `in`.readString()
        title = `in`.readString()
        description = `in`.readString()
        url = `in`.readString()!!
        urlToImage = `in`.readString()
        publishedAt = `in`.readString()
        content = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(author)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(url)
        parcel.writeString(urlToImage)
        parcel.writeString(publishedAt)
        parcel.writeString(content)
    }

    companion object {
        val CREATOR: Parcelable.Creator<Article?> = object : Parcelable.Creator<Article?> {
            override fun createFromParcel(`in`: Parcel): Article? {
                return Article(`in`)
            }

            override fun newArray(size: Int): Array<Article?> {
                return arrayOfNulls(size)
            }
        }
    }
}