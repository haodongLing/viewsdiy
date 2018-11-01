package com.haodong.brvahprac.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haodong.brvahprac.R;
import com.haodong.brvahprac.entity.ItemType;
import com.haodong.brvahprac.entity.MultipleItem;

import java.util.List;

/**
 * @author linghailong
 * @date on 2018/10/30
 * @email 105354999@qq.com
 * @describe :
 */
public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MultipleItemQuickAdapter(List<MultipleItem> data) {
        super(data);
        addItemType(ItemType.TEXT, R.layout.item_text_view);
        addItemType(ItemType.IMG, R.layout.item_img_view);
        addItemType(ItemType.IMG_TEXT, R.layout.item_img_text_view);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (helper.getItemViewType()) {
            case ItemType.TEXT:
                helper.setText(R.id.tv_item_text_view, item.getContent());
                break;
            case ItemType.IMG:
                switch (helper.getLayoutPosition() %
                        2) {
                    case 0:
                        helper.setImageResource(R.id.iv, R.mipmap.animation_img1);
                        break;
                    case 1:
                        helper.setImageResource(R.id.iv, R.mipmap.animation_img2);
                        break;

                }
                break;
            default:
                break;
        }
    }

}
