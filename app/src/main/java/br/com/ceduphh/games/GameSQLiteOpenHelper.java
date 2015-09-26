package br.com.ceduphh.games;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class GameSQLiteOpenHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "GamesDB";
    private static final int DATABASE_VERSION = 1;

    public GameSQLiteOpenHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql =
                /**/"CREATE TABLE GAMES ("+
                /**/"    ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,"+
                /**/"    NAME TEXT NOT NULL," +
                /**/"    AGE INTEGER NOT NULL"+
                /**/")";
        db.execSQL(sql);
        sql =
            /**/"CREATE TABLE PLATFORMS (" +
            /**/"    ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE" +
            /**/"    NAME TEXT NOT NULL" +
            /**/")";
        db.execSQL(sql);
        sql =
            /**/"CREATE TABLE GAMES_PLATFORMS (" +
            /**/"   GAME INTEGER NOT NULL, "+
            /**/"   PLATAFORM INTEGER NOT NULL"+
            /**/"   FOREIGN KEY(GAME) REFERENCES GAMES(ID)"+
            /**/"   FOREIGN KEY(PLATAFORM) REFERENCES PLATAFORMS(ID)"+
            /**/")";
        db.execSQL(sql);
        sql =
            /**/"INSERT INTO GAMES VALUES "+
            /**/"(\"Uncharted 3\", 12),"+
            /**/"(\"God of War\", 18),"+
            /**/"(\"Battlefield 3\", 16),"+
            /**/"(\"Mario Galaxy 2\", 6),"+
            /**/"(\"Okami\", 0),"+
            /**/"(\"New Super Mario Bross Wii\", 0);"+
            "INSERT INTO PLATAFORMS VALUES "+
            /**/"   (\"Playstation 3\"), "+
            /**/"   (\"Nintendo Wii\"); "+
                "INSERT INTO GAMES_PLATAFORMS VALUES "+
            /**/"(1,1),"+
            /**/"(2,1),"+
            /**/"(3,1),"+
            /**/"(4,2),"+
            /**/"(5,2),"+
            /**/"(6,2),";

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<Game> getGames(){
     Cursor cursor =   getReadableDatabase().query(true, "GAMES",
                new String[]{"ID","NOME","AGE"},
                null,null,null,null,"NAME",null);
//        getReadableDatabase().rawQuery("SELECT * FROM GAMES", null);

        List<Game> games = new ArrayList<Game>(cursor.getCount());

        int idIndex = cursor.getColumnIndex("ID");
        int nameIndex = cursor.getColumnIndex("NAME");
        int ageIndex = cursor.getColumnIndex("AGE");

        cursor.moveToFirst();
        while (cursor.moveToNext()){
            Game game = new Game();
            game.setId(cursor.getInt(idIndex));
            game.setName(cursor.getString(nameIndex));
            game.setAge(cursor.getInt(ageIndex));
            games.add(game);
        }
        return games;
    }
}
