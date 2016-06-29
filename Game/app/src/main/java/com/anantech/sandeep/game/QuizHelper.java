package com.anantech.sandeep.game;

/**
 * Created by Sandeep on 18-06-2016.
 */
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QuizHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "mathsone";
    // tasks table name
    private static final String TABLE_QUEST = "quest";
    // tasks Table Columns names
    private static final String KEY_ID = "qid";
    private static final String KEY_QUES = "question";
    private static final String KEY_ANSWER = "answer"; // correct option
    private static final String KEY_OPTA = "opta"; // option a
    private static final String KEY_OPTB = "optb"; // option b
    private static final String KEY_OPTC = "optc"; // option c

    private SQLiteDatabase dbase;

    public QuizHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase = db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER + " TEXT, " + KEY_OPTA + " TEXT, "
                + KEY_OPTB + " TEXT, " + KEY_OPTC + " TEXT)";
        db.execSQL(sql);
        addQuestion();
        // db.close();
    }

    private void addQuestion() {
        Question q1 = new Question("Barabati Stadium is located in which city", "Kanpur", "Cuttack", "Gwalior", "Cuttack");
        this.addQuestion(q1);
        Question q2 = new Question("Barkatullah Khan Stadium  is located in which city", "Kanpur", "Jodhpur", "Gwalior", "Jodhpur");
        this.addQuestion(q2);
        Question q3 = new Question("K. D. Singh Babu Stadium  is located in which city", "Kanpur", "Lucknow", "Indore", "Lucknow");
        this.addQuestion(q3);
        Question q4 = new Question("Green Park Stadium  is located in which city", "Kanpur", "Lucknow", "Indore", "Kanpur");
        this.addQuestion(q4);
        Question q5 = new Question("Captain Roop Singh Stadium  is located in which city", "Kanpur", "Gwalior", "Jaipur", "Gwalior");
        this.addQuestion(q5);
        Question q6 = new Question("Khandheri Cricket Stadium is located in which city", "Rajkot", "Gwalior", "Jaipur", "Rajkot");
        this.addQuestion(q6);
        Question q7 = new Question("Chinnaswamy Stadium  is located in which city", "Bangalore", "Chennai", "Hyderabad", "Bangalore");
        this.addQuestion(q7);
        Question q8 = new Question("Chepauk Stadium  is located in which city", "Vijaywada", "Chennai", "Hyderabad", "Chennai");
        this.addQuestion(q8);
        Question q9 = new Question("Rajasekhara Reddy Cricket Stadium  is located in which city", "Vijaywada", "Visakhapatnam", "Hyderabad", "Visakhapatnam");
        this.addQuestion(q9);
        Question q10 = new Question("Feroz Shah Kotla Cricket Stadium  is located in which city", "Delhi", "Ajmer", "Bhopal", "Delhi");
        this.addQuestion(q10);
        Question q11 = new Question("Eden Garden Cricket Stadium  is located in which city", "Delhi", "Calcutta", "London", "Calcutta");
        this.addQuestion(q11);
        Question q12 = new Question("Lord's Cricket Ground  is located in which city", "Sydney", "Calcutta", "London", "Calcutta");
        this.addQuestion(q12);
        Question q13 = new Question("Kingsmead Cricket Ground  is located in which city", "Durban", "Auckland", "Perth", "Durban");
        this.addQuestion(q13);
        Question q14 = new Question("The Gabba  is located in which city", "Brisbane", "Auckland", "Perth", "Brisbane");
        this.addQuestion(q14);
        Question q15 = new Question("Wanderers Stadium  is located in which city", "Johannesburg", "Auckland", "Perth", "Johannesburg");
        this.addQuestion(q15);
        Question q16 = new Question("Old Trafford Stadium  is located in which city", "Manchester", "Auckland", "Perth", "Manchester");
        this.addQuestion(q16);
        Question q17 = new Question("Brabourne Stadium is located in which city", "Mumbai", "Auckland", "Perth", "Mumbai");
        this.addQuestion(q17);
        Question q18 = new Question("DY Patil Stadium is located in which city", "Navi Mumbai", "Mumbai", "Pune", "Navi Mumbai");
        this.addQuestion(q18);
        Question q19 = new Question("Gaddafi Stadium is located in which city", "Lahore", "Multan", "Karachi", "Lahore");
        this.addQuestion(q19);
        Question q20 = new Question("Sawai Mansingh Stadium is located in which city", "Jaipur", "Jodhpur", "Gwalior", "Jaipur");
        this.addQuestion(q20);
        Question q21 = new Question("Sabina Park is located in which city", "Kingston", "Saint Lucia", "Cardiff", "Kingston");
        this.addQuestion(q21);
        // END
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
        // Create tables again
        onCreate(db);
    }

    // Adding new question
    public void addQuestion(Question quest) {
        // SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_ANSWER, quest.getANSWER());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        values.put(KEY_OPTC, quest.getOPTC());

        // Inserting Row
        dbase.insert(TABLE_QUEST, null, values);
    }

    public List<Question> getAllQuestions() {
        List<Question> quesList = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));

                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        // return quest list
        return quesList;
    }

}

