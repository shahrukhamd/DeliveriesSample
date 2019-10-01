package com.sasiddiqui.deliveriessample.repository.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sasiddiqui.deliveriessample.screen_delivery_list.data.model.DeliveryItem
import com.sasiddiqui.deliveriessample.screen_delivery_list.data.DeliveryItemDao

/**
 * The Room database for this app
 */
@Database(entities = [DeliveryItem::class],
        version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun deliveryItemDao(): DeliveryItemDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database.
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "deliveries-sample-db")
//                    .addCallback(object : RoomDatabase.Callback() {
//                        override fun onCreate(db: SupportSQLiteDatabase) {
//                            super.onCreate(db)
                            //TODO initialize database with sample data
//                        }
//                    })
                    .build()
        }
    }
}
