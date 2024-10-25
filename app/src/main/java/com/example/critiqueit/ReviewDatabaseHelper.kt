package com.example.critiqueit

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ReviewDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

  companion object {
    private const val DATABASE_NAME = "MovieReviews.db"
    private const val DATABASE_VERSION = 1
    const val TABLE_NAME = "reviews"
    const val COLUMN_ID = "id"
    const val COLUMN_TITLE = "movieTitle"
    const val COLUMN_REVIEW = "reviewText"
    const val COLUMN_RATING = "rating"
    const val COLUMN_DATE = "reviewDate"
  }

  override fun onCreate(db: SQLiteDatabase) {
    val createTable = """CREATE TABLE $TABLE_NAME (
            $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_TITLE TEXT,
            $COLUMN_REVIEW TEXT,
            $COLUMN_RATING REAL,
            $COLUMN_DATE INTEGER
        )"""
    db.execSQL(createTable)
  }

  override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
    onCreate(db)
  }
}