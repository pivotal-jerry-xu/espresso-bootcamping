package com.example.weatherapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class WeatherDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 6;

    static final String DATABASE_NAME = "weather.db";

    public WeatherDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createLocationTableQuery = "CREATE TABLE " + WeatherContract.LocationEntry.TABLE_NAME + " (" +
                WeatherContract.LocationEntry._ID + " INTEGER PRIMARY KEY," +
                WeatherContract.LocationEntry.COLUMN_LOCATION_SETTING + " TEXT UNIQUE NOT NULL, " +
                WeatherContract.LocationEntry.COLUMN_CITY_NAME + " TEXT NOT NULL, " +
                WeatherContract.LocationEntry.COLUMN_COUNTRY_NAME + " TEXT NOT NULL, " +
                WeatherContract.LocationEntry.COLUMN_COORD_LAT + " REAL NOT NULL, " +
                WeatherContract.LocationEntry.COLUMN_COORD_LONG + " REAL NOT NULL " +
                " );";

        db.execSQL(createLocationTableQuery);

        String createWeatherTableQuery = "CREATE TABLE " + WeatherContract.WeatherEntry.TABLE_NAME + " (" +
                WeatherContract.WeatherEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                WeatherContract.WeatherEntry.COLUMN_LOC_KEY + " INTEGER NOT NULL, " +
                WeatherContract.WeatherEntry.COLUMN_DATE + " INTEGER NOT NULL, " +
                WeatherContract.WeatherEntry.COLUMN_SHORT_DESC + " TEXT NOT NULL, " +
                WeatherContract.WeatherEntry.COLUMN_WEATHER_ID + " INTEGER NOT NULL," +
                WeatherContract.WeatherEntry.COLUMN_MIN_TEMP + " REAL NOT NULL, " +
                WeatherContract.WeatherEntry.COLUMN_MAX_TEMP + " REAL NOT NULL, " +
                WeatherContract.WeatherEntry.COLUMN_HUMIDITY + " REAL NOT NULL, " +
                WeatherContract.WeatherEntry.COLUMN_PRESSURE + " REAL NOT NULL, " +
                WeatherContract.WeatherEntry.COLUMN_WIND_SPEED + " REAL NOT NULL, " +
                WeatherContract.WeatherEntry.COLUMN_DEGREES + " REAL NOT NULL, " +
                " FOREIGN KEY (" + WeatherContract.WeatherEntry.COLUMN_LOC_KEY + ") REFERENCES " +
                WeatherContract.LocationEntry.TABLE_NAME + " (" + WeatherContract.LocationEntry._ID + "), " +
                " UNIQUE (" + WeatherContract.WeatherEntry.COLUMN_DATE + ", " +
                WeatherContract.WeatherEntry.COLUMN_LOC_KEY + ") ON CONFLICT REPLACE);";

        db.execSQL(createWeatherTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + WeatherContract.LocationEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + WeatherContract.WeatherEntry.TABLE_NAME);
        onCreate(db);
    }
}
