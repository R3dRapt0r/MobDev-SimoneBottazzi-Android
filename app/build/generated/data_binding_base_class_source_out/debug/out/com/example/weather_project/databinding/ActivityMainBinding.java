// Generated by view binder compiler. Do not edit!
package com.example.weather_project.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.weather_project.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final Button btnCities;

  @NonNull
  public final Button btnMap;

  @NonNull
  public final TextView currentDayDTemp;

  @NonNull
  public final TextView currentDayDescription;

  @NonNull
  public final RelativeLayout currentLayout;

  @NonNull
  public final ImageView imageView1;

  @NonNull
  public final ImageView imageView2;

  @NonNull
  public final ImageView imageView3;

  @NonNull
  public final ImageView imageView4;

  @NonNull
  public final ImageView imageView5;

  @NonNull
  public final ImageView imageView6;

  @NonNull
  public final ImageView imageView7;

  @NonNull
  public final ImageView imageView8;

  @NonNull
  public final LinearLayout layout1;

  @NonNull
  public final LinearLayout layout2;

  @NonNull
  public final LinearLayout layout3;

  @NonNull
  public final LinearLayout layout4;

  @NonNull
  public final LinearLayout layout5;

  @NonNull
  public final LinearLayout layout6;

  @NonNull
  public final LinearLayout layout7;

  @NonNull
  public final LinearLayout layout8;

  @NonNull
  public final TextView txtview11;

  @NonNull
  public final TextView txtview12;

  @NonNull
  public final TextView txtview13;

  @NonNull
  public final TextView txtview21;

  @NonNull
  public final TextView txtview22;

  @NonNull
  public final TextView txtview23;

  @NonNull
  public final TextView txtview31;

  @NonNull
  public final TextView txtview32;

  @NonNull
  public final TextView txtview33;

  @NonNull
  public final TextView txtview41;

  @NonNull
  public final TextView txtview42;

  @NonNull
  public final TextView txtview43;

  @NonNull
  public final TextView txtview51;

  @NonNull
  public final TextView txtview52;

  @NonNull
  public final TextView txtview53;

  @NonNull
  public final TextView txtview61;

  @NonNull
  public final TextView txtview62;

  @NonNull
  public final TextView txtview63;

  @NonNull
  public final TextView txtview71;

  @NonNull
  public final TextView txtview72;

  @NonNull
  public final TextView txtview73;

  @NonNull
  public final TextView txtview81;

  @NonNull
  public final TextView txtview82;

  @NonNull
  public final TextView txtview83;

  @NonNull
  public final LinearLayout verticalLayout;

  private ActivityMainBinding(@NonNull RelativeLayout rootView, @NonNull Button btnCities,
      @NonNull Button btnMap, @NonNull TextView currentDayDTemp,
      @NonNull TextView currentDayDescription, @NonNull RelativeLayout currentLayout,
      @NonNull ImageView imageView1, @NonNull ImageView imageView2, @NonNull ImageView imageView3,
      @NonNull ImageView imageView4, @NonNull ImageView imageView5, @NonNull ImageView imageView6,
      @NonNull ImageView imageView7, @NonNull ImageView imageView8, @NonNull LinearLayout layout1,
      @NonNull LinearLayout layout2, @NonNull LinearLayout layout3, @NonNull LinearLayout layout4,
      @NonNull LinearLayout layout5, @NonNull LinearLayout layout6, @NonNull LinearLayout layout7,
      @NonNull LinearLayout layout8, @NonNull TextView txtview11, @NonNull TextView txtview12,
      @NonNull TextView txtview13, @NonNull TextView txtview21, @NonNull TextView txtview22,
      @NonNull TextView txtview23, @NonNull TextView txtview31, @NonNull TextView txtview32,
      @NonNull TextView txtview33, @NonNull TextView txtview41, @NonNull TextView txtview42,
      @NonNull TextView txtview43, @NonNull TextView txtview51, @NonNull TextView txtview52,
      @NonNull TextView txtview53, @NonNull TextView txtview61, @NonNull TextView txtview62,
      @NonNull TextView txtview63, @NonNull TextView txtview71, @NonNull TextView txtview72,
      @NonNull TextView txtview73, @NonNull TextView txtview81, @NonNull TextView txtview82,
      @NonNull TextView txtview83, @NonNull LinearLayout verticalLayout) {
    this.rootView = rootView;
    this.btnCities = btnCities;
    this.btnMap = btnMap;
    this.currentDayDTemp = currentDayDTemp;
    this.currentDayDescription = currentDayDescription;
    this.currentLayout = currentLayout;
    this.imageView1 = imageView1;
    this.imageView2 = imageView2;
    this.imageView3 = imageView3;
    this.imageView4 = imageView4;
    this.imageView5 = imageView5;
    this.imageView6 = imageView6;
    this.imageView7 = imageView7;
    this.imageView8 = imageView8;
    this.layout1 = layout1;
    this.layout2 = layout2;
    this.layout3 = layout3;
    this.layout4 = layout4;
    this.layout5 = layout5;
    this.layout6 = layout6;
    this.layout7 = layout7;
    this.layout8 = layout8;
    this.txtview11 = txtview11;
    this.txtview12 = txtview12;
    this.txtview13 = txtview13;
    this.txtview21 = txtview21;
    this.txtview22 = txtview22;
    this.txtview23 = txtview23;
    this.txtview31 = txtview31;
    this.txtview32 = txtview32;
    this.txtview33 = txtview33;
    this.txtview41 = txtview41;
    this.txtview42 = txtview42;
    this.txtview43 = txtview43;
    this.txtview51 = txtview51;
    this.txtview52 = txtview52;
    this.txtview53 = txtview53;
    this.txtview61 = txtview61;
    this.txtview62 = txtview62;
    this.txtview63 = txtview63;
    this.txtview71 = txtview71;
    this.txtview72 = txtview72;
    this.txtview73 = txtview73;
    this.txtview81 = txtview81;
    this.txtview82 = txtview82;
    this.txtview83 = txtview83;
    this.verticalLayout = verticalLayout;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnCities;
      Button btnCities = ViewBindings.findChildViewById(rootView, id);
      if (btnCities == null) {
        break missingId;
      }

      id = R.id.btnMap;
      Button btnMap = ViewBindings.findChildViewById(rootView, id);
      if (btnMap == null) {
        break missingId;
      }

      id = R.id.currentDayDTemp;
      TextView currentDayDTemp = ViewBindings.findChildViewById(rootView, id);
      if (currentDayDTemp == null) {
        break missingId;
      }

      id = R.id.currentDayDescription;
      TextView currentDayDescription = ViewBindings.findChildViewById(rootView, id);
      if (currentDayDescription == null) {
        break missingId;
      }

      RelativeLayout currentLayout = (RelativeLayout) rootView;

      id = R.id.imageView1;
      ImageView imageView1 = ViewBindings.findChildViewById(rootView, id);
      if (imageView1 == null) {
        break missingId;
      }

      id = R.id.imageView2;
      ImageView imageView2 = ViewBindings.findChildViewById(rootView, id);
      if (imageView2 == null) {
        break missingId;
      }

      id = R.id.imageView3;
      ImageView imageView3 = ViewBindings.findChildViewById(rootView, id);
      if (imageView3 == null) {
        break missingId;
      }

      id = R.id.imageView4;
      ImageView imageView4 = ViewBindings.findChildViewById(rootView, id);
      if (imageView4 == null) {
        break missingId;
      }

      id = R.id.imageView5;
      ImageView imageView5 = ViewBindings.findChildViewById(rootView, id);
      if (imageView5 == null) {
        break missingId;
      }

      id = R.id.imageView6;
      ImageView imageView6 = ViewBindings.findChildViewById(rootView, id);
      if (imageView6 == null) {
        break missingId;
      }

      id = R.id.imageView7;
      ImageView imageView7 = ViewBindings.findChildViewById(rootView, id);
      if (imageView7 == null) {
        break missingId;
      }

      id = R.id.imageView8;
      ImageView imageView8 = ViewBindings.findChildViewById(rootView, id);
      if (imageView8 == null) {
        break missingId;
      }

      id = R.id.layout1;
      LinearLayout layout1 = ViewBindings.findChildViewById(rootView, id);
      if (layout1 == null) {
        break missingId;
      }

      id = R.id.layout2;
      LinearLayout layout2 = ViewBindings.findChildViewById(rootView, id);
      if (layout2 == null) {
        break missingId;
      }

      id = R.id.layout3;
      LinearLayout layout3 = ViewBindings.findChildViewById(rootView, id);
      if (layout3 == null) {
        break missingId;
      }

      id = R.id.layout4;
      LinearLayout layout4 = ViewBindings.findChildViewById(rootView, id);
      if (layout4 == null) {
        break missingId;
      }

      id = R.id.layout5;
      LinearLayout layout5 = ViewBindings.findChildViewById(rootView, id);
      if (layout5 == null) {
        break missingId;
      }

      id = R.id.layout6;
      LinearLayout layout6 = ViewBindings.findChildViewById(rootView, id);
      if (layout6 == null) {
        break missingId;
      }

      id = R.id.layout7;
      LinearLayout layout7 = ViewBindings.findChildViewById(rootView, id);
      if (layout7 == null) {
        break missingId;
      }

      id = R.id.layout8;
      LinearLayout layout8 = ViewBindings.findChildViewById(rootView, id);
      if (layout8 == null) {
        break missingId;
      }

      id = R.id.txtview1_1;
      TextView txtview11 = ViewBindings.findChildViewById(rootView, id);
      if (txtview11 == null) {
        break missingId;
      }

      id = R.id.txtview1_2;
      TextView txtview12 = ViewBindings.findChildViewById(rootView, id);
      if (txtview12 == null) {
        break missingId;
      }

      id = R.id.txtview1_3;
      TextView txtview13 = ViewBindings.findChildViewById(rootView, id);
      if (txtview13 == null) {
        break missingId;
      }

      id = R.id.txtview2_1;
      TextView txtview21 = ViewBindings.findChildViewById(rootView, id);
      if (txtview21 == null) {
        break missingId;
      }

      id = R.id.txtview2_2;
      TextView txtview22 = ViewBindings.findChildViewById(rootView, id);
      if (txtview22 == null) {
        break missingId;
      }

      id = R.id.txtview2_3;
      TextView txtview23 = ViewBindings.findChildViewById(rootView, id);
      if (txtview23 == null) {
        break missingId;
      }

      id = R.id.txtview3_1;
      TextView txtview31 = ViewBindings.findChildViewById(rootView, id);
      if (txtview31 == null) {
        break missingId;
      }

      id = R.id.txtview3_2;
      TextView txtview32 = ViewBindings.findChildViewById(rootView, id);
      if (txtview32 == null) {
        break missingId;
      }

      id = R.id.txtview3_3;
      TextView txtview33 = ViewBindings.findChildViewById(rootView, id);
      if (txtview33 == null) {
        break missingId;
      }

      id = R.id.txtview4_1;
      TextView txtview41 = ViewBindings.findChildViewById(rootView, id);
      if (txtview41 == null) {
        break missingId;
      }

      id = R.id.txtview4_2;
      TextView txtview42 = ViewBindings.findChildViewById(rootView, id);
      if (txtview42 == null) {
        break missingId;
      }

      id = R.id.txtview4_3;
      TextView txtview43 = ViewBindings.findChildViewById(rootView, id);
      if (txtview43 == null) {
        break missingId;
      }

      id = R.id.txtview5_1;
      TextView txtview51 = ViewBindings.findChildViewById(rootView, id);
      if (txtview51 == null) {
        break missingId;
      }

      id = R.id.txtview5_2;
      TextView txtview52 = ViewBindings.findChildViewById(rootView, id);
      if (txtview52 == null) {
        break missingId;
      }

      id = R.id.txtview5_3;
      TextView txtview53 = ViewBindings.findChildViewById(rootView, id);
      if (txtview53 == null) {
        break missingId;
      }

      id = R.id.txtview6_1;
      TextView txtview61 = ViewBindings.findChildViewById(rootView, id);
      if (txtview61 == null) {
        break missingId;
      }

      id = R.id.txtview6_2;
      TextView txtview62 = ViewBindings.findChildViewById(rootView, id);
      if (txtview62 == null) {
        break missingId;
      }

      id = R.id.txtview6_3;
      TextView txtview63 = ViewBindings.findChildViewById(rootView, id);
      if (txtview63 == null) {
        break missingId;
      }

      id = R.id.txtview7_1;
      TextView txtview71 = ViewBindings.findChildViewById(rootView, id);
      if (txtview71 == null) {
        break missingId;
      }

      id = R.id.txtview7_2;
      TextView txtview72 = ViewBindings.findChildViewById(rootView, id);
      if (txtview72 == null) {
        break missingId;
      }

      id = R.id.txtview7_3;
      TextView txtview73 = ViewBindings.findChildViewById(rootView, id);
      if (txtview73 == null) {
        break missingId;
      }

      id = R.id.txtview8_1;
      TextView txtview81 = ViewBindings.findChildViewById(rootView, id);
      if (txtview81 == null) {
        break missingId;
      }

      id = R.id.txtview8_2;
      TextView txtview82 = ViewBindings.findChildViewById(rootView, id);
      if (txtview82 == null) {
        break missingId;
      }

      id = R.id.txtview8_3;
      TextView txtview83 = ViewBindings.findChildViewById(rootView, id);
      if (txtview83 == null) {
        break missingId;
      }

      id = R.id.verticalLayout;
      LinearLayout verticalLayout = ViewBindings.findChildViewById(rootView, id);
      if (verticalLayout == null) {
        break missingId;
      }

      return new ActivityMainBinding((RelativeLayout) rootView, btnCities, btnMap, currentDayDTemp,
          currentDayDescription, currentLayout, imageView1, imageView2, imageView3, imageView4,
          imageView5, imageView6, imageView7, imageView8, layout1, layout2, layout3, layout4,
          layout5, layout6, layout7, layout8, txtview11, txtview12, txtview13, txtview21, txtview22,
          txtview23, txtview31, txtview32, txtview33, txtview41, txtview42, txtview43, txtview51,
          txtview52, txtview53, txtview61, txtview62, txtview63, txtview71, txtview72, txtview73,
          txtview81, txtview82, txtview83, verticalLayout);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
