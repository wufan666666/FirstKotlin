package com.wf.lifecycle.databinding.recycleView;

import com.wf.lifecycle.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : wf
 * @date : 2021年11月09日 11:00
 */
public class CatList {
    public static List<Cat> getCatList() {
        List<Cat> catList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            catList.add(new Cat("乖乖", "此猫猫为非卖品", R.mipmap.cat1));
            catList.add(new Cat("六一", "此猫猫为非卖品", R.mipmap.cat2));
            catList.add(new Cat("皮皮", "此猫猫为非卖品", R.mipmap.cat3));
            catList.add(new Cat("猪猪", "此猫猫为非卖品", R.mipmap.cat4));
            catList.add(new Cat("西巴", "此猫猫为非卖品", R.mipmap.cat5));
            catList.add(new Cat("尼奥", "此猫猫为非卖品", R.mipmap.cat6));
            catList.add(new Cat("初晴", "此猫猫为非卖品", R.mipmap.cat7));
            catList.add(new Cat("清迈", "此猫猫为非卖品", R.mipmap.cat8));
            catList.add(new Cat("倪菲", "此猫猫为非卖品", R.mipmap.cat9));
            catList.add(new Cat("十几", "此猫猫为非卖品", R.mipmap.cat10));
            catList.add(new Cat("楠楠", "此猫猫为非卖品", R.mipmap.cat12));
            catList.add(new Cat("灵笼", "此猫猫为非卖品", R.mipmap.cat11));
        }
        return catList;


    }
}