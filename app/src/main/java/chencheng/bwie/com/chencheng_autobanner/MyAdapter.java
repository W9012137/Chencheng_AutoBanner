package chencheng.bwie.com.chencheng_autobanner;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dell on 2017/12/2.
 */

public class MyAdapter extends LinearLayout {
    private ViewPager vp;
    private LinearLayout ll;
    private List<ImageView> imglist = new ArrayList<>();
    Handler handler = new Handler();
    private int index = 1;
    private int position;


    public MyAdapter(Context context) {
        this(context, null);
    }

    public MyAdapter(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        LayoutInflater.from(context).inflate(R.layout.other, this);
        vp = findViewById(R.id.iv_pager);
        ll = findViewById(R.id.ll);
    }

    public MyAdapter(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setData(List<String> list) {
        if (list == null) {
            throw new RuntimeException("集合不能为空");
        }
        //因为传过来的list里存的是图片下载地址，所以，vp的适配器，不能用
        for (int i = 0; i < list.size(); i++) {
            ImageView imageView = new ImageView(getContext());
            imglist.add(imageView);
            ImageLoader.getInstance().displayImage(list.get(i), imageView);
            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Intent intent = new Intent(getContext(), WebViewAcitivity.class);
                    intent.putExtra("url", "http://www.baidu.com");
                    getContext().startActivity(intent);
                }
            });
            //创建小圆点
            ImageView iv = new ImageView(getContext());
            LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.leftMargin = 5;
            iv.setLayoutParams(layoutParams);
            iv.setBackgroundResource(R.drawable.im_flase);
            ll.addView(iv);
        }
        ImageView ivm = (ImageView) ll.getChildAt(0);
        ivm.setBackgroundResource(R.drawable.im_true);
        final Adapter adapter = new Adapter();
        vp.setAdapter(adapter);
        vp.setOnPageChangeListener(new MyPageChangeListener());
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        vp.setCurrentItem((++index)%imglist.size());
                    }
                });
            }
        },1000,3000);

    }
 class MyPageChangeListener implements ViewPager.OnPageChangeListener{


     @Override
     public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

     }


     @Override
     public void onPageSelected(int position) {
      //重置所有的圆点装点
         reset();
         ImageView iv= (ImageView) ll.getChildAt(position);
         iv.setBackgroundResource(R.drawable.im_true);


     }


     @Override
     public void onPageScrollStateChanged(int state) {

     }
 }
public void reset(){
    int childCount=ll.getChildCount();
    for (int i=0;i<childCount;i++){
        ImageView iv= (ImageView) ll.getChildAt(i);
        iv.setBackgroundResource(R.drawable.im_flase);

    }
}

class Adapter extends PagerAdapter{


    @Override
    public int getCount() {
        return imglist.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView=imglist.get(position);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView((View) object);
    }
}

}
