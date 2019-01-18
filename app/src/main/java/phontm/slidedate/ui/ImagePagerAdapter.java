package phontm.slidedate.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import phontm.slidedate.R;

public class ImagePagerAdapter extends PagerAdapter {

    List<String> pages = null;
    LayoutInflater mLayoutInflater;
    AdapterListener mListener;
    boolean[] clickedYes;
    boolean[] clickedNo;
    Context context;
    String[] q1;
    String[] q2;
    String[] q3;
    boolean[] selectedFirstAnswer;
    boolean[] selectedSecondAnswer;
    boolean[] selectedThirdAnswer;
    boolean[] selectedFourthAnswer;
    String finalText;

    public ImagePagerAdapter(List<String> pages, String[] q1, String[] q2, String[] q3, String finalText, Context context){
        this.pages = pages;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.finalText = finalText;
        mLayoutInflater = LayoutInflater.from(context);
        mListener = (AdapterListener) context;
        clickedNo = new boolean[pages.size()];
        clickedYes = new boolean[pages.size()];
        selectedFirstAnswer = new boolean[3];
        selectedSecondAnswer = new boolean[3];
        selectedThirdAnswer = new boolean[3];
        selectedFourthAnswer = new boolean[3];
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View itemView = null;
//        mListener.onButtonClicked(false);
        if (position < 6) {
            itemView = mLayoutInflater.inflate(R.layout.pager_item_image, container, false);

            ImageView imageView = itemView.findViewById(R.id.image_view);
            final ImageButton yes = itemView.findViewById(R.id.btn_yes);
            final ImageButton no = itemView.findViewById(R.id.btn_no);
            yes.setBackground(clickedYes[position] ? context.getResources().getDrawable(R.color.selected) : context.getResources().getDrawable(R.color.transarent));
            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickedYes[position] = !clickedYes[position];
                    if (clickedYes[position]) {
                        clickedNo[position] = false;
                    }
                    yes.setBackground(clickedYes[position] ? context.getResources()
                            .getDrawable(R.color.selected) : context.getResources().getDrawable(R.color.transarent));
                    no.setBackground(clickedNo[position] ? context.getResources()
                            .getDrawable(R.color.selected) : context.getResources().getDrawable(R.color.transarent));
                    mListener.onButtonClicked(clickedYes[position] || clickedNo[position]);
                }
            });
            no.setBackground(clickedNo[position] ? context.getResources().getDrawable(R.color.selected) : context.getResources().getDrawable(R.color.transarent));
            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickedNo[position] = !clickedNo[position];
                    if (clickedNo[position]) {
                        clickedYes[position] = false;
                    }
                    yes.setBackground(clickedYes[position] ? context.getResources()
                            .getDrawable(R.color.selected) : context.getResources().getDrawable(R.color.transarent));
                    no.setBackground(clickedNo[position] ? context.getResources()
                            .getDrawable(R.color.selected) : context.getResources().getDrawable(R.color.transarent));
                    mListener.onButtonClicked(clickedYes[position] || clickedNo[position]);
                }
            });
            final ProgressBar pb = itemView.findViewById(R.id.homeprogress);
            Picasso.get().load(pages.get(position)).into(imageView, new Callback() {
                @Override
                public void onSuccess() {
                    pb.setVisibility(View.GONE);
                }

                @Override
                public void onError(Exception e) {

                }
            });
        } else if (position < 9) {
            itemView = mLayoutInflater.inflate(R.layout.pager_item_question, container, false);
            TextView q = itemView.findViewById(R.id.tv_question);
            final TextView a1 = itemView.findViewById(R.id.tv_answer_one);
            final TextView a2 = itemView.findViewById(R.id.tv_answer_two);
            final TextView a3 = itemView.findViewById(R.id.tv_answer_three);
            final TextView a4 = itemView.findViewById(R.id.tv_answer_four);
            final RadioButton r1 = itemView.findViewById(R.id.radio_one);
            final RadioButton r2 = itemView.findViewById(R.id.radio_two);
            final RadioButton r3 = itemView.findViewById(R.id.radio_three);
            final RadioButton r4 = itemView.findViewById(R.id.radio_four);
            a1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setTextChoice(a1,a2,a3,a4,r1,r2,r3,r4,0,position-6);
                }
            });
            a2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setTextChoice(a1,a2,a3,a4,r1,r2,r3,r4,1,position-6);
                }
            });
            a3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setTextChoice(a1,a2,a3,a4,r1,r2,r3,r4,2,position-6);
                }
            });
            a4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setTextChoice(a1,a2,a3,a4,r1,r2,r3,r4,3,position-6);
                }
            });
//            r1.setEnabled(false);
//            r2.setEnabled(false);
//            r3.setEnabled(false);
//            r4.setEnabled(false);
            r1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setTextChoice(a1,a2,a3,a4,r1,r2,r3,r4,0,position-6);
                }
            });
            r2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setTextChoice(a1,a2,a3,a4,r1,r2,r3,r4,1,position-6);
                }
            });
            r3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setTextChoice(a1,a2,a3,a4,r1,r2,r3,r4,2,position-6);
                }
            });
            r4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setTextChoice(a1,a2,a3,a4,r1,r2,r3,r4,3,position-6);
                }
            });
            Button continueBtn = itemView.findViewById(R.id.cont_btn);
            continueBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectedFirstAnswer[position-6]||selectedSecondAnswer[position-6]
                            ||selectedThirdAnswer[position-6]||selectedFourthAnswer[position-6]) {
                        mListener.onButtonClicked(true);
                    }
                }
            });
            switch (position){
                case 6:
                    q.setText(q1[0]);
                    a1.setText(q1[1]);
                    a2.setText(q1[2]);
                    a3.setText(q1[3]);
                    a4.setText(q1[4]);
                    break;
                case 7:
                    q.setText(q2[0]);
                    a1.setText(q2[1]);
                    a2.setText(q2[2]);
                    a3.setText(q2[3]);
                    a4.setText(q2[4]);
                    break;
                case 8:
                    q.setText(q3[0]);
                    a1.setText(q3[1]);
                    a2.setText(q3[2]);
                    a3.setText(q3[3]);
                    a4.setText(q3[4]);
                    break;
            }
        } else {
            itemView = mLayoutInflater.inflate(R.layout.pager_item_final, container, false);
            TextView tv = itemView.findViewById(R.id.final_text);
            tv.setText(finalText);
            Button btnClose = itemView.findViewById(R.id.btn_close);
            btnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onFinish();
                }
            });
        }
        container.addView(itemView);

        return itemView;
    }

    private void setTextChoice (TextView a1, TextView a2, TextView a3, TextView a4,
                                RadioButton r1, RadioButton r2,RadioButton r3,RadioButton r4,int selected, int pos) {
        switch (selected) {
            case 0:
                selectedFirstAnswer[pos] = !selectedFirstAnswer[pos];
                selectedSecondAnswer[pos] = false;
                selectedThirdAnswer[pos] = false;
                selectedFourthAnswer[pos] = false;
                break;
            case 1:
                selectedFirstAnswer[pos] = false;
                selectedSecondAnswer[pos] = !selectedSecondAnswer[pos];
                selectedThirdAnswer[pos] = false;
                selectedFourthAnswer[pos] = false;
                break;
            case 2:
                selectedFirstAnswer[pos] = false;
                selectedSecondAnswer[pos] = false;
                selectedThirdAnswer[pos] = !selectedThirdAnswer[pos];
                selectedFourthAnswer[pos] = false;
                break;
            case 3:
                selectedFirstAnswer[pos] = false;
                selectedSecondAnswer[pos] = false;
                selectedThirdAnswer[pos] = false;
                selectedFourthAnswer[pos] = !selectedFourthAnswer[pos];
                break;
        }
        a1.setBackground(context.getResources().getDrawable(selectedFirstAnswer[pos] ? R.color.answer_select : R.color.selected));
        a2.setBackground(context.getResources().getDrawable(selectedSecondAnswer[pos] ? R.color.answer_select : R.color.selected));
        a3.setBackground(context.getResources().getDrawable(selectedThirdAnswer[pos] ? R.color.answer_select : R.color.selected));
        a4.setBackground(context.getResources().getDrawable(selectedFourthAnswer[pos] ? R.color.answer_select : R.color.selected));
        r1.setChecked(selectedFirstAnswer[pos]);
        r2.setChecked(selectedSecondAnswer[pos]);
        r3.setChecked(selectedThirdAnswer[pos]);
        r4.setChecked(selectedFourthAnswer[pos]);
//        mListener.onButtonClicked(selectedFirstAnswer[pos]||selectedSecondAnswer[pos]||selectedThirdAnswer[pos]||selectedFourthAnswer[pos]);
    }

    @Override
    public int getCount() {
        return pages.size() + 4;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public interface AdapterListener {
        void onButtonClicked(boolean clicked);
        void onFinish();
    }
}
