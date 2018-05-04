package com.example.utkarshyadavin.stacksearch.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.utkarshyadavin.stacksearch.R;
import com.example.utkarshyadavin.stacksearch.models.Question;
import com.example.utkarshyadavin.stacksearch.models.User;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by utkarshyadavin on 2/5/18.
 */

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {

    private Context context ;
    private List<Question> questions ;

    public QuestionAdapter (Context context , List<Question> questions){
        this.context = context ;
        this.questions = questions ;
    }

    public class QuestionViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView userProfileImage ;
        private TextView username ;
        private TextView questionTitle ;
        private TextView questionDescription ;

        public QuestionViewHolder( View mView){
            super(mView);
            userProfileImage = (CircleImageView) mView.findViewById(R.id.user_profile_imageview);
            username = (TextView) (TextView) mView.findViewById(R.id.username) ;
            questionTitle = (TextView) mView.findViewById(R.id.question_title);
            questionDescription = (TextView) mView.findViewById(R.id.question_description);
        }
    }

    @Override
    public QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_list_item , parent, false);
        return new QuestionViewHolder(itemView) ;
    }

    @Override
    public void onBindViewHolder(QuestionViewHolder holder, int position) {

        final Question mQuestion  = questions.get(position);
        User user = mQuestion.getOwner() ;

        holder.username.setText(user.getUsername());
        String htmlBoldText = "<b>" + mQuestion.getQuestionTitle() + "</b>" ;
        holder.questionTitle.setText(Html.fromHtml(htmlBoldText));
        holder.questionDescription.setText(Html.fromHtml(mQuestion.getQuestiondetails()));

        Picasso.with(context).load(user.getProfileImageUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.userProfileImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Do something
                String questionUrl = mQuestion.getQuestionLink() ;
                Intent startWebBrowser = new Intent(Intent.ACTION_VIEW) ;
                startWebBrowser.setData(Uri.parse(questionUrl)) ;
                context.startActivity(startWebBrowser);
            }
        });
    }

    @Override
    public int getItemCount() {

        return questions.size() ;
    }
}
