package qrcodescanner.masterteam.com.masterandroid.helpers.util.database;

import android.content.Context;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import qrcodescanner.masterteam.com.masterandroid.helpers.model.Code;
import qrcodescanner.masterteam.com.masterandroid.helpers.model.CodeDao;

public class DatabaseUtil {

    private static DatabaseUtil sInstance;
    private CodeDao mCodeDao;

    private DatabaseUtil() {
        setCodeDao(QrCobaDatabase.on().codeDao());
    }

    public static void init(Context context) {
        QrCobaDatabase.init(context);

        if (sInstance == null) {
            sInstance = new DatabaseUtil();
        }
    }

    public static DatabaseUtil on() {
        if (sInstance == null) {
            sInstance = new DatabaseUtil();
        }

        return sInstance;
    }

    private CodeDao getCodeDao() {
        return mCodeDao;
    }

    private void setCodeDao(CodeDao codeDao) {
        mCodeDao = codeDao;
    }

    public Completable insertCode(Code code) {
        return getCodeDao().insert(code);
    }

    public Flowable<List<Code>> getAllCodes() {
        return getCodeDao().getAllFlowableCodes();
    }
    public int deleteEntity(Code code) {
        return getCodeDao().delete(code);
    }
}
