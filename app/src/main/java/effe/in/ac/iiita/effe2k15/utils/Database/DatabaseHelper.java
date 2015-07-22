package effe.in.ac.iiita.effe2k15.utils.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Simplifies Operation on Database
 */
public class DatabaseHelper
{

    public Database database;
    Context context;
    SQLiteDatabase db;

    public DatabaseHelper(Context context)
    {
        database = new Database(context);
        this.context = context;
        db = database.getWritableDatabase();

    }

    /**
     *
     * @param question Question text
     * @param answer Answer
     * @param question_number Question Number
     * @param option Options
     * @return success/failure
     */
    public long insertData(String question, String answer, int question_number, String[] option)
    {


        ContentValues contentValues = new ContentValues();
        contentValues.put(Database.QUESTION_TEXT, question);
        contentValues.put(Database.QUESTION_ANSWER, answer);
        contentValues.put(Database.Q_ID, question_number);
        contentValues.put(Database.QUESTION_OPTION1, option[0]);
        contentValues.put(Database.QUESTION_OPTION2, option[1]);
        contentValues.put(Database.QUESTION_OPTION3, option[2]);
        contentValues.put(Database.QUESTION_OPTION4, option[3]);
        contentValues.put(Database.QUESTION_OPTION5, option[4]);


        return db.insertWithOnConflict(Database.TABLE_QUESTION_TEXT, null,
                contentValues, SQLiteDatabase.CONFLICT_REPLACE);

    }

    /**
     *
     * @param q_id whose answer is required
     * @return answer of q_id
     */
    public int getQuestionAnswer(int q_id)
    {
        String[] columns = {Database.QUESTION_ANSWER};
        Cursor cursor = db.query(Database.TABLE_QUESTION_TEXT, columns,
                Database.Q_ID + "='" + q_id + "'", null, null, null, null);
        int answer = -1;
        while (cursor.moveToNext())
        {
            int index = cursor.getColumnIndex(Database.QUESTION_ANSWER);
            answer = cursor.getInt(index);
        }
        return answer;
    }

    /**
     *
     * @param q_id question id whose question text is required
     * @return question text
     */
    public String getData(int q_id)
    {
        String[] columns = {Database.Q_ID, Database.QUESTION_TEXT};
        Cursor cursor = db.query(Database.TABLE_QUESTION_TEXT, columns,
                Database.Q_ID + "='" + q_id + "'", null, null, null, null);
        StringBuilder buffer = new StringBuilder();
        while (cursor.moveToNext())
        {
            int index = cursor.getColumnIndex(Database.QUESTION_TEXT);
            String sQUESTION_TEXT = cursor.getString(index);
            buffer.append(sQUESTION_TEXT);
        }
        return buffer.toString();
    }

    /**
     *
     * @param q_id whose response is required
     * @return return user response of question with index q_id
     */
    public int getResponseData(int q_id)
    {
        String[] columns = {Database.QUESTION_RESPONSE};
        Cursor cursor = db.query(Database.TABLE_QUESTION_TEXT, columns,
                Database.Q_ID + "='" + q_id + "'", null, null, null, null);
        int response = 0;
        try
        {
            cursor.moveToFirst();
            response = cursor.getInt(0);
        } catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return response;

    }

    /**
     *
     * @param response user response , i.e 0,1,2,3,4 (Simply Option number)
     * @param q_id question index
     * @return success/failure
     */
    public long insertResponse(int response, int q_id)
    {
        long id;
        ContentValues contentValues = new ContentValues();
        contentValues.put(Database.QUESTION_RESPONSE, response);
        id = db.update(Database.TABLE_QUESTION_TEXT, contentValues, Database.Q_ID + "=" + q_id, null);
        return id;
    }

    /**
     *
     * @param q_id question index
     * @return  question option as array list
     */
    public ArrayList<String> getOptionData(int q_id)
    {
        String[] columns = {Database.QUESTION_OPTION1, Database.QUESTION_OPTION2, Database.QUESTION_OPTION3, Database.QUESTION_OPTION4, Database.QUESTION_OPTION5};
        Cursor cursor = db.query(Database.TABLE_QUESTION_TEXT, columns,
                Database.Q_ID + "=" + q_id + "", null, null, null, null);
        ArrayList<String> string = new ArrayList<>();
        if (cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            string.add(cursor.getString(0));
            string.add(cursor.getString(1));
            string.add(cursor.getString(2));
            string.add(cursor.getString(3));
            string.add(cursor.getString(4));
        }
        return string;
    }

}
