package com.jsontodos.jsonplaceholdertodos.models

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todoItems")
class TodosData() : Parcelable {

    @ColumnInfo(name = "userId")
    var userId: Int = 0

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "title")
    var title: String = ""

    @ColumnInfo(name = "completed")
    var completed: Boolean = false

    constructor(userId: Int, id: Int, title: String, completed: Boolean) : this() {
        this.userId = userId
        this.id = id
        this.title = title
        this.completed = completed
    }

    constructor(parcel: Parcel) : this() {
        userId = parcel.readInt()
        id = parcel.readInt()
        title = parcel.readString().toString()
        completed = parcel.readByte() != 0.toByte()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(userId)
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeByte(if (completed) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TodosData> {
        override fun createFromParcel(parcel: Parcel): TodosData {
            return TodosData(parcel)
        }

        override fun newArray(size: Int): Array<TodosData?> {
            return arrayOfNulls(size)
        }
    }


}