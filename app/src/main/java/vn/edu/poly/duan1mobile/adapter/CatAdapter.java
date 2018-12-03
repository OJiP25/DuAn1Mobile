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
import vn.edu.poly.duan1mobile.model.Cat;
import vn.edu.poly.duan1mobile.sqlitedao.CatDAO;

public class CatAdapter extends BaseAdapter implements Filterable{
    List<Cat> arrCat;
    List<Cat> arrSortCat;
    private Filter catFilter;
    public Activity context;
    public LayoutInflater inflater;
    CatDAO bookDAO;

    public CatAdapter(Activity context, List<Cat> arrCat) {
        super();
        this.arrCat = arrCat;
        this.arrSortCat = arrCat;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.bookDAO = new CatDAO(context);
    }

    @Override
    public int getCount() {
        return arrCat.size();
    }
    @Override
    public Object getItem(int position) {
        return arrCat.get(position);
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
                    bookDAO.deleteCatByID(arrCat.get(position).getEdtNamepet());
                    arrCat.remove(position);
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        Cat _entry = (Cat) arrCat.get(position);
        holder.img.setImageResource(R.drawable.cat1);
        holder.txtCatName.setText("Name: " + _entry.getEdtNamepet());
        holder.txtCharac.setText("Characteristics: " + _entry.getEdtCharacteristics());
        holder.txtPrice.setText("Price: " + _entry.getEdtPrice());
        holder.txtLink.setText("Link: " + _entry.getEdtLink());
        return convertView;
    }

    @Override
    public Filter getFilter() {
        if (catFilter == null)
            catFilter = new CustomFilter();
        return catFilter;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void resetData() {
        arrCat = arrSortCat;
    }

    public void changeDataset(List<Cat> items) {
        this.arrCat = items;
        notifyDataSetChanged();
    }

    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
                results.values = arrSortCat;
                results.count = arrSortCat.size();
            } else {
                List<Cat> lsBook = new ArrayList<Cat>();
                for (Cat p : arrCat) {
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
                arrCat = (List<Cat>) results.values;
                notifyDataSetChanged();
            }
        }
    }
}
