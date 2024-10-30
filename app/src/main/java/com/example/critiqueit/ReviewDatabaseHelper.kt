import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.critiqueit.Review

class ReviewDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

  companion object {
    private const val DATABASE_NAME = "reviews.db"
    private const val DATABASE_VERSION = 1
  }

  override fun onCreate(db: SQLiteDatabase) {
    val createTable = """
            CREATE TABLE reviews (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                summary TEXT,
                sentiment TEXT,
                confidence REAL
            )
        """
    db.execSQL(createTable)
  }

  override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    db.execSQL("DROP TABLE IF EXISTS reviews")
    onCreate(db)
  }

  fun insertReview(summary: String, sentiment: String, confidence: Double) {
    val db = this.writableDatabase
    val values = ContentValues().apply {
      put("summary", summary)
      put("sentiment", sentiment)
      put("confidence", confidence) // Store confidence as Double
    }

    try {
      db.insert("reviews", null, values)
    } catch (e: Exception) {
      e.printStackTrace() // Log the error or handle it as necessary
    } finally {
      db.close()
    }
  }

  fun getAllReviews(): List<Review> {
    val reviews = mutableListOf<Review>()
    val db = this.readableDatabase

    // Using use to ensure cursor is closed automatically
    db.rawQuery("SELECT * FROM reviews", null).use { cursor ->
      // Get column indices once, instead of retrieving them multiple times in the loop
      val idIndex = cursor.getColumnIndexOrThrow("id")
      val summaryIndex = cursor.getColumnIndexOrThrow("summary")
      val sentimentIndex = cursor.getColumnIndexOrThrow("sentiment")
      val confidenceIndex = cursor.getColumnIndexOrThrow("confidence")

      while (cursor.moveToNext()) { // Iterate through the cursor
        val review = Review(
          id = cursor.getInt(idIndex),
          summary = cursor.getString(summaryIndex),
          sentiment = cursor.getString(sentimentIndex),
          confidence = cursor.getDouble(confidenceIndex)
        )
        reviews.add(review)
      }
    }

    db.close() // Close the database after operation
    return reviews
  }

  // Additional methods can be added here for retrieving by ID or other criteria
}
