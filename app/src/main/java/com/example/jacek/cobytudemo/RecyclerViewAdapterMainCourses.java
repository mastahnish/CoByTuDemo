package com.example.jacek.cobytudemo;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.github.lzyzsd.circleprogress.DonutProgress;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecyclerViewAdapterMainCourses extends
        RecyclerView.Adapter<RecyclerViewAdapterMainCourses.MyViewHolder> {

    public static float SIZE_RATIO_82 = (float) 0.25625;
    public static float SIZE_RATIO_54 = (float) 0.16875;
    public static float SIZE_RATIO_30 = (float) 0.09375;
    public static float SIZE_RATIO_16 = (float) 0.05;
    public static float SIZE_RATIO_12 = (float) 0.0375;

    private LayoutInflater inflater;
    private Context context;
    private ClickListener clickListener;
    List<MainCourse> userMainCourses = Collections.emptyList(); // new ArrayList<>();

    public RecyclerViewAdapterMainCourses(Context context,
                                          List<MainCourse> userMainCoursesValues) {
        this.inflater = LayoutInflater.from(context);
        this.userMainCourses = userMainCoursesValues;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (!userMainCourses.isEmpty()) {
            view = inflater
                    .inflate(R.layout.recycler_view_list_row_main_courses,
                            parent, false);
        } else {
            view = inflater.inflate(
                    R.layout.recycler_view_list_row_main_courses_empty, parent,
                    false);
        }
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        if (!userMainCourses.isEmpty()) {

            MainCourse singleMainCourse = userMainCourses.get(position);

            final float screenWidthPx = holder.itemView.getResources()
                    .getDisplayMetrics().widthPixels;
            final float screenWidthDp = UsefulFunctions.convertPixelsToDp(
                    screenWidthPx, context);

            // Zdjecie
            String url = "http://cobytu.com/foto/"
//					+ singleMainCourse.getRestaurateurID().toString()
//					+ "/"
                    + singleMainCourse.getPhotoURL().trim()
                    .replace("_m.jpg", ".jpg").replace(" ","%20");
            // Foto
            holder.nivMainCourseFoto
                    .setDefaultImageResId(R.drawable.background_image);
            holder.nivMainCourseFoto.setImageUrl(url, App.getInstance()
                    .getImageLoader());
            holder.nivMainCourseFoto.getLayoutParams().height = (int) (screenWidthPx * 0.75);
            Log.d("cobytu", "[AdapterMainCourses] photo url: " + url);

            // Nazwa
            holder.tvMainCourseName.setText(singleMainCourse
                    .getMainCourseName());
            holder.tvMainCourseName.setShadowLayer((float) 0.01, (float) 0.25,
                    (float) 0.25, Color.WHITE);

            // Wskaznik
            if(false){
                holder.dpEvaluation.setVisibility(View.VISIBLE);
            }else{
                holder.dpEvaluation.setVisibility(View.INVISIBLE);
            }

            holder.dpEvaluation.setSuffixText("");
            holder.dpEvaluation.setTextColor(ContextCompat.getColor(context, android.R.color.holo_red_dark));
            holder.dpEvaluation.setFinishedStrokeColor(ContextCompat.getColor(context, android.R.color.holo_red_dark));
//            holder.dpEvaluation.setStartingDegree(270);
//			holder.dpEvaluation.setProgress(0);
            holder.dpEvaluation.setProgress(Integer.parseInt(singleMainCourse.getEvaluation()));
            final int progressFinal = Integer.parseInt(singleMainCourse.getEvaluation());
//			holder.dpEvaluation.set

/*
			final MyViewHolder holderFinal = holder;
			final Timer timer;
			timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					((Activity) context).runOnUiThread(new Runnable() {
						@Override
						public void run() {
//							Log.d("cobytu", getClass().getName()+ ": dpEvaluation.getProgress(): "+ holderFinal.dpEvaluation.getProgress() + " VS. progressFinal: " + progressFinal);
							if (holderFinal.dpEvaluation.getProgress() == progressFinal) {
								timer.cancel();
							} else {
								holderFinal.dpEvaluation
										.setProgress(holderFinal.dpEvaluation
												.getProgress() + 1);
							}

						}
					});
				}
			}, 0, 5);*/

            // Cena
            if (singleMainCourse.getPrice().equals("wr")) {
                holder.tvMainCoursePrice.setText(context.getString(R.string.priceDependsOnLocation));
                holder.tvMainCoursePriceCurrency.setVisibility(View.GONE);
            } else {
                holder.tvMainCoursePrice.setText(singleMainCourse.getPrice());
                holder.tvMainCoursePrice.setShadowLayer(5, 0, 0, Color.WHITE);
                holder.tvMainCoursePrice.setTextSize((int) screenWidthDp
                        * SIZE_RATIO_16);
                holder.tvMainCoursePriceCurrency.setShadowLayer(5, 0, 0,
                        Color.WHITE);
                holder.tvMainCoursePriceCurrency
                        .setTextSize((int) screenWidthDp * SIZE_RATIO_12);
            }

            // Kcal
            holder.tvMainCourseKcal.setText(String.valueOf(singleMainCourse
                    .getKcal()) + " kcal");
            holder.tvMainCourseKcal.setShadowLayer(5, 0, 0, Color.WHITE);
            holder.tvMainCourseKcal.setTextSize((int) screenWidthDp
                    * SIZE_RATIO_12);

            // Time
            holder.tvMainCoursePreparationTime.setText(singleMainCourse
                    .getPreparationTime() + " min");
            holder.tvMainCoursePreparationTime.setShadowLayer(5, 0, 0,
                    Color.WHITE);
            holder.tvMainCoursePreparationTime.setTextSize((int) screenWidthDp
                    * SIZE_RATIO_12);

            // Waga
            holder.tvMainCourseWeight.setText(String.valueOf(singleMainCourse
                    .getWeight()) + " g");
            holder.tvMainCourseWeight.setShadowLayer(5, 0, 0, Color.WHITE);
            holder.tvMainCourseWeight.setTextSize((int) screenWidthDp
                    * SIZE_RATIO_12);




            if(singleMainCourse.getMainCourseAverageEvaluation() == null){
                singleMainCourse.setMainCourseAverageEvaluation("0.00");
            }

            String averageEvaluation = singleMainCourse.getMainCourseAverageEvaluation();

            Log.d("cobytu", getClass().getName() + " averageEvaluation: " + averageEvaluation);

            holder.rbMainCourseOpinionsAverageEvaluation.setRating(Float.parseFloat(averageEvaluation));
            holder.tvMainCourseOpinionsAverageEvaluation.setText(averageEvaluation);


        }

    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        if (userMainCourses.isEmpty()) {
            return 1;
        } else {
            return userMainCourses.size();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        NetworkImageView nivMainCourseFoto;
        TextView tvMainCourseName;
        final DonutProgress dpEvaluation;
        TextView tvMainCoursePrice;
        TextView tvMainCoursePriceCurrency;
        TextView tvMainCourseKcal;
        TextView tvMainCoursePreparationTime;
        TextView tvMainCourseWeight;
        RatingBar rbMainCourseOpinionsAverageEvaluation;
        TextView tvMainCourseOpinionsAverageEvaluation;

        public MyViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            nivMainCourseFoto = (NetworkImageView) itemView
                    .findViewById(R.id.niv_main_course_foto); // Zostaje

            // Nazwa
            tvMainCourseName = (TextView) itemView
                    .findViewById(R.id.tv_main_course_name); // Zostaje

            // Wskaznik
            dpEvaluation = (DonutProgress) itemView
                    .findViewById(R.id.dp_main_course_evaluation); // Zostaje

            // Cena
            tvMainCoursePrice = (TextView) itemView
                    .findViewById(R.id.tv_main_course_price); // Zostaje
            tvMainCoursePriceCurrency = (TextView) itemView
                    .findViewById(R.id.tv_main_course_price_currency); // Zostaje

            // kcal
            tvMainCourseKcal = (TextView) itemView
                    .findViewById(R.id.tv_main_course_kcal); // Zostaje

            // Czas
            tvMainCoursePreparationTime = (TextView) itemView
                    .findViewById(R.id.tv_main_course_preparation_time); // Zostaje

            // Waga
            tvMainCourseWeight = (TextView) itemView
                    .findViewById(R.id.tv_main_course_weight); // Zostaje

            //Srednia Ocen
            rbMainCourseOpinionsAverageEvaluation = (RatingBar) itemView.findViewById(R.id.rb_main_course_opinions_average_evaluation);
            tvMainCourseOpinionsAverageEvaluation = (TextView) itemView.findViewById(R.id.tv_main_course_opinions_average_evaluation);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.itemClick(v, getAdapterPosition());
            }

        }

    }

    public interface ClickListener {
        public void itemClick(View view, int position);
    }

    public void setList(List<MainCourse> list){
        userMainCourses = new ArrayList<>(list);
    }

    public void animateTo(List<MainCourse> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<MainCourse> newModels) {
        for (int i = userMainCourses.size() - 1; i >= 0; i--) {
            final MainCourse model = userMainCourses.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<MainCourse> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final MainCourse model = newModels.get(i);
            if (!userMainCourses.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<MainCourse> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final MainCourse model = newModels.get(toPosition);
            final int fromPosition = userMainCourses.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public MainCourse removeItem(int position) {
        final MainCourse model = userMainCourses.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, MainCourse model) {
        userMainCourses.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final MainCourse model = userMainCourses.remove(fromPosition);
        userMainCourses.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }
    
}