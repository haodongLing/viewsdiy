package com.example.chapter1.electric.ablm.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.chapter1.R;
import com.example.chapter1.electric.ablm.fragments.GalleryFragment;
import com.example.chapter1.electric.ablm.SlideImage;
import com.example.chapter1.electric.ablm.draghelper.ItemDragHelperCallback;
import com.example.chapter1.electric.ablm.tools.UiTool;
import com.example.matisse.Matisse;
import com.example.matisse.MimeType;
import com.example.matisse.engine.impl.Glide4Engine;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * description:
 * author: linghailong
 * date: 2019/3/20
 */
public class AblumActivity extends AppCompatActivity {
    private Button btnAdd, btnPreview, btnSet;
    private GalleryFragment galleryFragment;
    private RecyclerView recyclerView;
    private SlideImageAdapter mSlideImgAdapter;
    private static final int REQUEST_CODE_CHOOSE = 23;
    private int mScreenHeight;
    private int mScreenWidth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int hasReadStorage = ContextCompat.checkSelfPermission(this.getApplicationContext(),
                Manifest.permission
                        .READ_EXTERNAL_STORAGE);
        int hasWriteStorage = ContextCompat.checkSelfPermission(this.getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (hasReadStorage != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission
                    .READ_EXTERNAL_STORAGE}, 23);
        }
        if (hasWriteStorage != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission
                    .WRITE_EXTERNAL_STORAGE}, 23);
        }
        setContentView(R.layout.activity_ablum);
        initView();
    }

    private void initView() {
        this.mScreenWidth = UiTool.getScreenWidth(this);
        this.mScreenHeight = UiTool.getScreenHeight(this);
        btnAdd = findViewById(R.id.btn_ablum_add);
        btnPreview = findViewById(R.id.btn_ablum_preview);
        /*BtnPreview*/
        btnPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSlideImgAdapter != null) {
                    final ArrayList<SlideImage> arrayList = mSlideImgAdapter.getSlideImageArr();
                    if (arrayList != null && arrayList.size() != 0) {
                        Observable.create(new ObservableOnSubscribe<ArrayList<SlideImage>>() {
                            @Override
                            public void subscribe(ObservableEmitter<ArrayList<SlideImage>> emitter)
                                    throws Exception {
                                int size = arrayList.size();
                                for (int i = 0; i < size; i++) {
                                    String path = arrayList.get(i).getPath();
                                    BitmapFactory.Options options = new BitmapFactory.Options();
                                    options.inJustDecodeBounds = true;
                                    BitmapFactory.decodeFile(path, options);
                                    int width = options.outWidth;
                                    int height = options.outHeight;
                                    options.inJustDecodeBounds = false;
                                    Log.e("lhl", "subscribe: " +
                                            "width-->"+width+"mScreenWidth--->"+mScreenWidth+"height--->"+height+"mScreenHeight--->"+mScreenHeight);
                                    /*第一种情况*/
                                    if (width>mScreenWidth&&width>height){
                                        /*第二种情况：需要背景*/
                                        Log.e("lhl", "bindView:--->width >height ");
                                        arrayList.get(i).setId(1);
                                    }else  if (width<mScreenWidth||height<mScreenHeight) {
                                        if (width < height) {
                                            Log.e("lhl", "bindView:--->width < height ");
                                            arrayList.get(i).setId(0);
                                        }
                                    }
                                    else {
                                        /**/
                                        Log.e("lhl", "bindView++: ");
                                        arrayList.get(i).setId(2);
                                    }

                                }
                                emitter.onNext(arrayList);
                            }
                        }).subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<ArrayList<SlideImage>>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(ArrayList<SlideImage> slideImages) {
                                        Log.e("lhl", "onNext: ");
                                        Bundle bundle = new Bundle();
                                        Intent intent = new Intent(AblumActivity.this,
                                                PreviewActivity.class);
                                        intent.putParcelableArrayListExtra("slideArray",
                                                slideImages);
                                        startActivity(intent);
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.e("lhl", "onError: ");
                                    }

                                    @Override
                                    public void onComplete() {
                                        Log.e("lhl", "onComplete: ");
                                    }
                                });

                    } else {
                        Toast.makeText(AblumActivity.this, "you need to select photos first", Toast
                                .LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AblumActivity.this, "you need to select photos first", Toast
                            .LENGTH_SHORT).show();
                }


            }
        });
        btnSet = findViewById(R.id.btn_ablum_shezhi);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Matisse.from(AblumActivity.this)
                        .choose(MimeType.ofOnlyImage())
                        .theme(R.style.Matisse_Zhihu)
                        .countable(false)
                        .maxSelectable(9)
                        .originalEnable(true)
                        .maxOriginalSize(10)
                        .imageEngine(new Glide4Engine())
                        .forResult(REQUEST_CODE_CHOOSE);
            }
        });
        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        recyclerView = findViewById(R.id.ablum_recycler_view);
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(manager);

        ItemDragHelperCallback callback = new ItemDragHelperCallback() {
            @Override
            public boolean isLongPressDragEnabled() {
                // 长按拖拽打开
                return true;
            }
        };
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);
    }

    private void initAdapter(ArrayList<SlideImage> slideImages) {
        if (mSlideImgAdapter == null) {
            mSlideImgAdapter = new SlideImageAdapter(AblumActivity.this, slideImages);
            recyclerView.setAdapter(mSlideImgAdapter);
        } else {
            mSlideImgAdapter.setSlideImageArr(slideImages);
            mSlideImgAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            List<Uri> uriList = Matisse.obtainResult(data);
            List<String> stringList = Matisse.obtainPathResult(data);
            initAdapter(getSlideArray(uriList, stringList));
        }
    }

    private ArrayList<SlideImage> getSlideArray(List<Uri> uriList, List<String> pathList) {
        ArrayList<SlideImage> arrayList = new ArrayList<>();
        int size = uriList.size();
        for (int i = 0; i < size; i++) {
            SlideImage slideImage = new SlideImage();
            slideImage.setPath(pathList.get(i));
            slideImage.setUri(uriList.get(i));
            arrayList.add(slideImage);
        }
        return arrayList;
    }


    // 隐藏软件盘
    private void hideSoftKeyboard() {
        // 当前焦点的View
        View view = getCurrentFocus();
        if (view == null)
            return;

        InputMethodManager imm = (InputMethodManager) getSystemService(Context
                .INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
