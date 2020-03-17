package com.haodong.pracmodule.mycontentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.File;

/**
 * created by linghaoDo on 2020-03-12
 * description:
 * <p>
 * version:
 */
public class StudentContentProvider extends ContentProvider {
    public static String AUTHORITY = "com.haodong.pracmodule.mycontentprovider.StudentContentProvider";
    public static final Uri BOOK_CONTNET_URI = Uri.parse("content://" + AUTHORITY + File.separator + "book");
    public static final Uri BOOK_CONTNET_USER = Uri.parse("content://" + AUTHORITY + File.separator + "user");
    private static final UriMatcher sUriMathcher = new UriMatcher(UriMatcher.NO_MATCH);
    public static final int BOOK_URI_CODE=1;
    public static final int USER_URI_CODE=2;
    static {
        sUriMathcher.addURI(AUTHORITY,"book",BOOK_URI_CODE);
        sUriMathcher.addURI(AUTHORITY,"user",USER_URI_CODE);
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
