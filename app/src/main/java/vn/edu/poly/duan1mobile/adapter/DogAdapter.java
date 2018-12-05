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
import vn.edu.poly.duan1mobile.model.Dog;
import vn.edu.poly.duan1mobile.sqlitedao.DogDAO;

public class DogAdapter extends BaseAdapter implements Filterable{
    List<Dog> arrDog;
    List<Dog> arrSortDog;
    private Filter DogFilter;
    public Activity context;
    public LayoutInflater inflater;
    DogDAO dogDAO;

    public DogAdapter(Activity context, List<Dog> arrDog) {
        super();
        this.arrDog = arrDog;
        this.arrSortDog = arrDog;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.dogDAO = new DogDAO(context);
    }

    @Override
    public int getCount() {
        return arrDog.size();
    }
    @Override
    public Object getItem(int position) {
        return arrDog.get(position);
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
            holder = new DogAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.item_dog, null);
            holder.img = (ImageView) convertView.findViewById(R.id.imgDog);
            holder.txtCatName = (TextView) convertView.findViewById(R.id.edtNamepetd);
            holder.txtCharac = (TextView) convertView.findViewById(R.id.edtCharacteristicsd);
            holder.txtPrice = (TextView) convertView.findViewById(R.id.edtPriced);
            holder.txtLink = (TextView) convertView.findViewById(R.id.edtLinkd);
            holder.imgDelete = (ImageView) convertView.findViewById(R.id.imgcleard);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dogDAO.deleteCatByID(arrDog.get(position).getEdtNamepet());
                    arrDog.remove(position);
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        Dog _entry = (Dog) arrDog.get(position);
        holder.img.setImageResource(R.drawable.dog);
        holder.txtCatName.setText("Name: " + _entry.getEdtNamepet());
        holder.txtCharac.setText("Characteristics: " + _entry.getEdtCharacteristics());
        holder.txtPrice.setText("Price: " + _entry.getEdtPrice());
        holder.txtLink.setText("Link: " + _entry.getEdtLink());
        return convertView;
    }

    @Override
    public Filter getFilter() {
        if (DogFilter == null)
            DogFilter = new CustomFilter();
        return DogFilter;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void resetData() {
        arrDog= arrSortDog;
    }

    public void changeDataset(List<Dog> items) {
        this.arrDog = items;
        notifyDataSetChanged();
    }

    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
                results.values = arrSortDog;
                results.count = arrSortDog.size();
            } else {
                List<Dog> lsBook = new ArrayList<Dog>();
                for (Dog p : arrDog) {
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
                arrDog = (List<Dog>) results.values;
                notifyDataSetChanged();
            }
        }
    }
}
