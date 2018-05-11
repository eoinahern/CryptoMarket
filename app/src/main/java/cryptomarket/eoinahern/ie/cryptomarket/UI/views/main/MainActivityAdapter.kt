package cryptomarket.eoinahern.ie.cryptomarket.UI.views.main

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup


class MainActivityAdapter : RecyclerView.Adapter<MainActivityAdapter.ViewHolder>() {


	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}



	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{

		return ViewHolder(View(parent.context))
	}

	override fun getItemCount(): Int {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}


	class ViewHolder(item : View) : RecyclerView.ViewHolder(item) {

	}



}