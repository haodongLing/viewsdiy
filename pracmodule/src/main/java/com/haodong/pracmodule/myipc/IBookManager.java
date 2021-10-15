package com.haodong.pracmodule.myipc;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.haodong.pracmodule.myipc.IBookManager.Stub.DESCRIPTOR;
import static com.haodong.pracmodule.myipc.IBookManager.Stub.TRANSACTION_addBook;
import static com.haodong.pracmodule.myipc.IBookManager.Stub.TRANSACTION_getBook;

/**
 * Author: tangyuan
 * Time : 2021/9/30
 * Description: AIdl 源码分析 IBookManager
 */
public interface IBookManager extends IInterface {
    Book1 getBook();

    void addBook(Book1 book1);

    /*server端*/
    public static abstract class Stub extends Binder implements IBookManager {
        public static final String DESCRIPTOR = "com.haodong.pracmodule.myipc.IBookManager";
        public static final int TRANSACTION_getBook = FIRST_CALL_TRANSACTION;
        public static final int TRANSACTION_addBook = FIRST_CALL_TRANSACTION + 1;

        @Override
        protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case INTERFACE_TRANSACTION:
                    reply.writeString(DESCRIPTOR);
                    return true;
                case TRANSACTION_addBook:
                    data.enforceInterface(DESCRIPTOR);
                    Book1 book1 = null;
                    if (data.readInt() != 0) {
                        book1 = Book1.CREATOR.createFromParcel(data);
                    }
                    this.addBook(book1);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getBook:
                    data.enforceInterface(DESCRIPTOR);
                    Book1 _result = this.getBook();
                    reply.writeNoException();
                    if ((_result != null)) {
                        reply.writeInt(1);
                        _result.writeToParcel(reply, Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;


            }
            return super.onTransact(code, data, reply, flags);


        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        /**
         * 首先我们看asInterface方法，Binder驱动传来的IBinder对象，
         * 通过queryLocalInterface方法，查找本地Binder对象，如果返回的就是PersonManger，
         * 说明client和server处于同一个进程，直接返回，如果不是，返回给一个代理对象。
         */
        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        public static IBookManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (iInterface instanceof IBookManager) {
                return (IBookManager) iInterface;
            }
//            return new
            return new Proxy(obj);
        }

    }

    public static class Proxy implements IBookManager {
        private IBinder mRemote;

        public Proxy(IBinder remote) {
            mRemote = remote;
        }

        @Override
        public void addBook(Book1 book1) {
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            try {
                data.writeInterfaceToken(DESCRIPTOR);
                if (book1 != null) {
                    data.writeInt(1);
                    book1.writeToParcel(data, 0);
                } else {
                    data.writeInt(0);
                }
                mRemote.transact(TRANSACTION_addBook, data, reply, 0);
                reply.readException();
            } catch (Exception exception) {

            } finally {
                reply.recycle();
                data.recycle();

            }

        }

        @Override
        public Book1 getBook() {
            Parcel data = Parcel.obtain();
            Parcel replay = Parcel.obtain();
            Book1 result=null;
            try {
                data.writeInterfaceToken(DESCRIPTOR);
                mRemote.transact(TRANSACTION_getBook, data, replay, 0);
                replay.readException();
                if (replay.readInt() != 0) {
                    result = Book1.CREATOR.createFromParcel(replay);
                } else {
                    result = null;
                }
            } catch (Exception exception) {

            }finally {
                replay.recycle();
                data.recycle();
            }
            return result;
        }

        public String getInterfaceDescriptor() {
            return DESCRIPTOR;
        }


        @Override
        public IBinder asBinder() {
            return mRemote;
        }
    }

}
