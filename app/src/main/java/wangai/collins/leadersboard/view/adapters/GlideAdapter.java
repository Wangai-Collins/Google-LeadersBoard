package wangai.collins.leadersboard.view.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import wangai.collins.leadersboard.R;

public class GlideAdapter {

    @BindingAdapter("imageUrl")
    public static void setImage(ImageView view, String imageUrl){
        //get context
        Context context = view.getContext();

        //create request options for glide
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background);

        // now set image with glide
        Glide.with(context)
                .setDefaultRequestOptions(options)
                .load(imageUrl)
                .into(view);
    }


    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show){
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
