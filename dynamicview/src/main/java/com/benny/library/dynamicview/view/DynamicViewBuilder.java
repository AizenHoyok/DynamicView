package com.benny.library.dynamicview.view;

import android.content.Context;
import android.view.View;

import com.benny.library.dynamicview.setter.BackgroundSetter;
import com.benny.library.dynamicview.setter.LayoutGravitySetter;
import com.benny.library.dynamicview.setter.MarginSetter;
import com.benny.library.dynamicview.setter.PaddingSetter;
import com.benny.library.dynamicview.setter.RelativeSetter;
import com.benny.library.dynamicview.setter.SizeSetter;
import com.benny.library.dynamicview.setter.WeightSetter;

public abstract class DynamicViewBuilder {
    protected View view;
    private RelativeSetter relativeSetter = new RelativeSetter();
    private MarginSetter marginSetter = new MarginSetter();
    private PaddingSetter paddingSetter = new PaddingSetter();
    private BackgroundSetter backgroundSetter = new BackgroundSetter();
    private SizeSetter sizeSetter = new SizeSetter();
    private LayoutGravitySetter layoutGravitySetter = new LayoutGravitySetter();
    private WeightSetter weightSetter = new WeightSetter();

    abstract public void createView(Context context);

    public View getView() {
        return view;
    }

    public boolean setProperty(String key, String value) {
        switch (key) {
            case "id":
                view.setId(Integer.parseInt(value));
                break;
            case MarginSetter.PROPERTY:
                marginSetter.setMargin(view, value);
                return true;
            case PaddingSetter.PROPERTY:
                paddingSetter.setPadding(view, value);
                return true;
            case BackgroundSetter.PROPERTY:
                backgroundSetter.setBackground(view, value);
                return true;
            case SizeSetter.PROPERTY:
                sizeSetter.setSize(view, value);
                return true;
            case LayoutGravitySetter.PROPERTY:
                layoutGravitySetter.setGravity(view, value);
            case WeightSetter.PROPERTY:
                weightSetter.setWeight(view, value);
                return true;
            default:
                if (relativeSetter.canHandle(key)) {
                    relativeSetter.set(view, key, value);
                    return true;
                }
        }
        return false;
    }
}
