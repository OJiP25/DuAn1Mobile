package vn.edu.poly.duan1mobile.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.duan1mobile.R;
import vn.edu.poly.duan1mobile.model.Andmore;
import vn.edu.poly.duan1mobile.sqlitedao.AndMoreDAO;

public class AndMoreAdapter extends BaseAdapter implements Filterable{
    List<Andmore> arrAndmore;
    List<Andmore> arrSortAndmore;
    private Filter AndmoreFilter;
    public Activity context;
    public LayoutInflater inflater;
    AndMoreDAO moreDAO;

    public AndMoreAdapter(Activity context, List<Andmore> arrAndmore) {
        super();
        this.arrAndmore = arrAndmore;
        this.arrSortAndmore = arrAndmore;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.moreDAO = new AndMoreDAO(context);
    }

    @Override
    public int getCount() {
        return arrAndmore.size();
    }
    @Override
    public Object getItem(int position) {
        return arrAndmore.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        ImageView img;
        TextView txtCatName;
        TextView txtCharac;
        TextView txtPrice;
        TextView txtLink;
        ImageView imgDelete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_cat, null);
            holder.img = (ImageView) convertView.findViewById(R.id.imgCatc);
            holder.txtCatName = (TextView) convertView.findViewById(R.id.edtNamepetc);
            holder.txtCharac = (TextView) convertView.findViewById(R.id.edtCharacteristicsc);
            holder.txtPrice = (TextView) convertView.findViewById(R.id.edtPricec);
            holder.txtLink = (TextView) convertView.findViewById(R.id.edtLinkc);
            holder.imgDelete = (ImageView) convertView.findViewById(R.id.imgclear);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    moreDAO.deleteCatByID(arrAndmore.get(position).getEdtNamepet());
                    arrAndmore.remove(position);
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        Andmore _entry = (Andmore) arrAndmore.get(position);
        holder.img.setImageResource(R.drawable.fish);
        holder.txtCatName.setText("Name: " + _entry.getEdtNamepet());
        holder.txtCharac.setText("Characteristics: " + _entry.getEdtCharacteristics());
        holder.txtPrice.setText("Price: " + _entry.getEdtPrice());
        holder.txtLink.setText("Link: " + _entry.getEdtLink());
        return convertView;
    }

    @Override
    public Filter getFilter() {
        if (AndmoreFilter == null)
            AndmoreFilter = new CustomFilter();
        return AndmoreFilter;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void resetData() {
        arrAndmore = arrSortAndmore;
    }

    public void changeDataset(List<Andmore> items) {
        this.arrAndmore = items;
        notifyDataSetChanged();
    }

    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
                results.values = arrSortAndmore;
                results.count = arrSortAndmore.size();
            } else {
                List<Andmore> lsBook = new ArrayList<Andmore>();
                for (Andmore p : arrAndmore) {
                    if
                            (p.getEdtNamepet().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                        lsBook.add(p);
                }
                results.values = lsBook;
                results.count = lsBook.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count == 0) {
                notifyDataSetInvalidated();
            } else {
                arrAndmore = (List<Andmore>) results.values;
                notifyDataSetChanged();
            }
        }
    }
}
