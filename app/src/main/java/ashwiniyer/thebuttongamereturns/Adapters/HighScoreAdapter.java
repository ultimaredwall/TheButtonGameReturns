package ashwiniyer.thebuttongamereturns.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import ashwiniyer.thebuttongamereturns.R;
import ashwiniyer.thebuttongamereturns.Scores;

/**
 * Created by Ashwin on 08/11/2015.
 */
public class HighScoreAdapter extends BaseAdapter {
    private Context mContext;
    private Scores[] mScores;


    public HighScoreAdapter(Context context, Scores[] scores){
        mContext = context;
        mScores = scores;
    }

    @Override
    public int getCount() {
        return mScores.length;
    }

    @Override
    public Object getItem(int position) {
        return mScores[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;//we aren't going to use this
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;


        if (convertView == null){
            //brand new
            convertView = LayoutInflater.from(mContext).inflate(R.layout.highscore_list_item,null);
            holder = new ViewHolder();
            holder.positionTextView = (TextView) convertView.findViewById(R.id.positionTextView);
            holder.scoreTextView = (TextView) convertView.findViewById(R.id.scoreTextView);
            holder.nameTextView = (TextView) convertView.findViewById(R.id.nameTextView);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        Scores score = mScores[position];
        holder.positionTextView.setText(score.getPlace());
        holder.scoreTextView.setText(score.getScore());
        holder.nameTextView.setText(score.getName());
        return convertView;
    }

    private static class ViewHolder{
        TextView positionTextView;
        TextView scoreTextView;
        TextView nameTextView;
    }
}
