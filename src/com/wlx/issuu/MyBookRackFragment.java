package com.wlx.issuu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.FinalBitmap;
import android.content.Context;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wlx.issuu.model.HavePaidBook;
import com.wlx.issuu.widget.XListView;
import com.wlx.issuu.widget.XListView.IXListViewListener;

public class MyBookRackFragment extends BaseFragment implements
		IXListViewListener {
	private XListView mAdapterView = null;
	private StaggeredAdapter mAdapter = null;
	private int currentPage = 0;
	private ArrayList<HavePaidBook> mInfos = new ArrayList<HavePaidBook>();;
	ContentTask task = new ContentTask(getActivity(), 2);

	private class ContentTask extends
			AsyncTask<String, Integer, List<HavePaidBook>> {

		private Context mContext;
		private int mType = 1;

		public ContentTask(Context context, int type) {
			super();
			mContext = context;
			mType = type;
		}

		@Override
		protected List<HavePaidBook> doInBackground(String... params) {
			try {
				return parseNewsJSON(params[0]);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(List<HavePaidBook> result) {
			if (mType == 1) {

				mInfos.clear();
				mInfos.addAll(result);
				mAdapter.notifyDataSetChanged();
				mAdapterView.stopRefresh();

			} else if (mType == 2) {
				mAdapterView.stopLoadMore();
				mInfos.addAll(result);
				mAdapter.notifyDataSetChanged();
			}

		}

		@Override
		protected void onPreExecute() {
		}

		public List<HavePaidBook> parseNewsJSON(String url) throws IOException {
			List<HavePaidBook> havaPaidBooks = new ArrayList<HavePaidBook>();
			HavePaidBook HavePaidBook1 = new HavePaidBook();
			HavePaidBook HavePaidBook2 = new HavePaidBook();
			HavePaidBook HavePaidBook4 = new HavePaidBook();
			HavePaidBook HavePaidBook5 = new HavePaidBook();
			HavePaidBook1
					.setBookImgUrl("http://d.hiphotos.baidu.com/image/w%3D400/sign=5a808b8f6e81800a6ee5880e813433d6/a5c27d1ed21b0ef4900dea5edfc451da80cb3ecc.jpg");
			HavePaidBook2
					.setBookImgUrl("http://e.hiphotos.baidu.com/image/w%3D400/sign=b056ed995cdf8db1bc2e7d643923dddb/bba1cd11728b4710552411cdc1cec3fdfc0323ac.jpg");
			HavePaidBook4
					.setBookImgUrl("http://b.hiphotos.baidu.com/image/w%3D400/sign=e2288abba41ea8d38a227504a70b30cf/50da81cb39dbb6fd83b4d8260b24ab18962b37c6.jpg");
			HavePaidBook5
					.setBookImgUrl("http://g.hiphotos.baidu.com/image/w%3D400/sign=77706a22b11bb0518f24b228067ada77/09fa513d269759eee36a06cbb0fb43166d22dffc.jpg");
			havaPaidBooks.add(HavePaidBook1);
			havaPaidBooks.add(HavePaidBook2);
			havaPaidBooks.add(HavePaidBook4);
			havaPaidBooks.add(HavePaidBook5);

			// List<HavePaidBook> duitangs = new ArrayList<HavePaidBook>();
			// String json = "";
			// if (Helper.checkConnection(mContext)) {
			// try {
			// json = Helper.getStringFromUrl(url);
			//
			// } catch (IOException e) {
			// Log.e("IOException is : ", e.toString());
			// e.printStackTrace();
			// return duitangs;
			// }
			// }
			// Log.d("MainActiivty", "json:" + json);
			//
			// try {
			// if (null != json) {
			// JSONObject newsObject = new JSONObject(json);
			// JSONObject jsonObject = newsObject.getJSONObject("data");
			// JSONArray blogsJson = jsonObject.getJSONArray("blogs");
			//
			// for (int i = 0; i < blogsJson.length(); i++) {
			// JSONObject newsInfoLeftObject = blogsJson.getJSONObject(i);
			// HavePaidBook newsInfo1 = new HavePaidBook();
			// newsInfo1.setAlbid(newsInfoLeftObject.isNull("albid") ? "" :
			// newsInfoLeftObject.getString("albid"));
			// newsInfo1.setIsrc(newsInfoLeftObject.isNull("isrc") ? "" :
			// newsInfoLeftObject.getString("isrc"));
			// newsInfo1.setMsg(newsInfoLeftObject.isNull("msg") ? "" :
			// newsInfoLeftObject.getString("msg"));
			// newsInfo1.setHeight(newsInfoLeftObject.getInt("iht"));
			// duitangs.add(newsInfo1);
			// }
			// }
			// } catch (JSONException e) {
			// e.printStackTrace();
			// }
			return havaPaidBooks;
		}
	}

	/**
	 * 添加内容
	 * 
	 * @param pageindex
	 * @param type
	 *            1为下拉刷新 2为加载更多
	 */
	private void AddItemToContainer(int pageindex, int type) {
		if (task.getStatus() != Status.RUNNING) {
			String url = "http://www.duitang.com/album/1733789/masn/p/"
					+ pageindex + "/24/";
			Log.d("MainActivity", "current url:" + url);
			ContentTask task = new ContentTask(getActivity(), type);
			task.execute(url);

		}
	}

	public class StaggeredAdapter extends BaseAdapter {
		private Context mContext;
		private ArrayList<HavePaidBook> mInfos;
		private FinalBitmap finalBitmap;

		public StaggeredAdapter(Context context, ArrayList<HavePaidBook> mInfos) {
			mContext = context;
			this.mInfos = mInfos;
			finalBitmap = FinalBitmap.create(context);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.infos_list, null);
				holder.imageView = (ImageView) convertView
						.findViewById(R.id.news_pic);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			HavePaidBook HavePaidBook = mInfos.get(position);

			finalBitmap.display(holder.imageView, HavePaidBook.getBookImgUrl());
			// holder.imageView.setImageWidth(HavePaidBook.getWidth());
			// holder.imageView.setImageHeight(HavePaidBook.getHeight());
			// holder.contentView.setText(HavePaidBook.getMsg());
			// mImageFetcher.loadImage(HavePaidBook.getIsrc(),
			// holder.imageView);
			return convertView;
		}

		class ViewHolder {
			ImageView imageView;
			TextView contentView;
			TextView timeView;
		}

		@Override
		public int getCount() {
			return mInfos.size();
		}

		@Override
		public Object getItem(int arg0) {
			return mInfos.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_mybookract, null);

		this.initView(view);
		return view;
	}

	private void initView(View view) {
		mAdapterView = (XListView) view.findViewById(R.id.list);
		mAdapterView.setPullLoadEnable(true);
		mAdapterView.setXListViewListener(this);

		mAdapter = new StaggeredAdapter(getActivity(), mInfos);
		mAdapterView.setAdapter(mAdapter);
		AddItemToContainer(currentPage, 2);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		return true;
	}

	@Override
	public void onResume() {
		super.onResume();

	}

	@Override
	public void onRefresh() {
		AddItemToContainer(++currentPage, 1);

	}

	@Override
	public void onLoadMore() {
		AddItemToContainer(++currentPage, 2);

	}

}
