package vn.edu.poly.duan1mobile.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import vn.edu.poly.duan1mobile.model.User;
import vn.edu.poly.duan1mobile.sqlitedao.UserDAO;

public class UserAdapter extends BaseAdapter {

    List<User> arrUser;
    public Activity context;
    public LayoutInflater inflater;
    UserDAO userDAO;

    public UserAdapter(List<User> arrUser, Activity context) {
        super();
        this.arrUser = arrUser;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        userDAO = new UserDAO(context);
    }

    @Override
    public int getCount() {
        return arrUser.size();
    }

    @Override
    public Object getItem(int i) {
        return arrUser.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }


}
