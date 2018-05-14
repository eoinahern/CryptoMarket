package cryptomarket.eoinahern.ie.cryptomarket.UI.views.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cryptomarket.eoinahern.ie.cryptomarket.R
import cryptomarket.eoinahern.ie.cryptomarket.data.models.CryptoCurrency
import javax.inject.Inject


class MainActivityAdapter @Inject constructor(val presenter : MainActivityPresenter) : RecyclerView.Adapter<MainActivityAdapter.ViewHolder>() {

	private lateinit var cryptoList : HashMap<String, CryptoCurrency>

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {

		holder.itemView.setOnClickListener { presenter.navigateToDetail() }
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{

		val v  = LayoutInflater.from(parent.context).inflate(R.layout.main_recycler_item, parent, false)
		val vh =  ViewHolder(v)
		vh.itemView.setOnClickListener { presenter.navigateToDetail() }

		//vh.adapterPosition
		return vh
	}

	override fun getItemCount(): Int {
		return cryptoList.size
	}


	class ViewHolder(item : View) : RecyclerView.ViewHolder(item) {


	}



}