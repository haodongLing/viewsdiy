package com.example.matisse.engine.impl;

import android.content.Context;

import com.example.matisse.MimeType;
import com.example.matisse.filter.Filter;
import com.example.matisse.internal.entity.IncapableCause;
import com.example.matisse.internal.entity.Item;

import java.util.HashSet;
import java.util.Set;

/**
 * description:
 * author: linghailong
 * date: 2019/3/22
 */
public class ImageFilter extends Filter {
    @Override
    protected Set<MimeType> constraintTypes() {
        return new HashSet<MimeType>() {{
            add(MimeType.GIF);
        }};
    }

    @Override
    public IncapableCause filter(Context context, Item item) {
        return null;
    }
}
