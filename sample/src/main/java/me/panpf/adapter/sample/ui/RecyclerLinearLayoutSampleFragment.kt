package me.panpf.adapter.sample.ui

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fm_recycler.*
import me.panpf.adapter.AssemblyAdapter
import me.panpf.adapter.AssemblyRecyclerAdapter
import me.panpf.adapter.more.OnLoadMoreListener
import me.panpf.adapter.sample.R
import me.panpf.adapter.sample.bean.Game
import me.panpf.adapter.sample.bean.User
import me.panpf.adapter.sample.item.GameItem
import me.panpf.adapter.sample.item.HeaderItem
import me.panpf.adapter.sample.item.LoadMoreItem
import me.panpf.adapter.sample.item.UserItem
import java.lang.ref.WeakReference

class RecyclerLinearLayoutSampleFragment : BaseFragment(), OnLoadMoreListener {

    var nextStart: Int = 0
    var size = 20

    private val adapter = AssemblyRecyclerAdapter().apply {
        addHeaderItem(HeaderItem.Factory(), "我是小额头呀！")
        addHeaderItem(HeaderItem.Factory(), "唉，我的小额头呢？")
        addItemFactory(UserItem.Factory())
        addItemFactory(GameItem.Factory())
        addFooterItem(HeaderItem.Factory(), "我是小尾巴呀！")
        addFooterItem(HeaderItem.Factory(), "唉，我的小尾巴呢？")
        setMoreItem(LoadMoreItem.Factory(this@RecyclerLinearLayoutSampleFragment))
    }

    override fun onUserVisibleChanged(isVisibleToUser: Boolean) {
        val attachActivity = activity
        if (isVisibleToUser && attachActivity is AppCompatActivity) {
            attachActivity.supportActionBar?.subtitle = "RecyclerView - LinearLayoutManager"
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fm_recycler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerFm_recycler.layoutManager = LinearLayoutManager(activity)
        recyclerFm_recycler.adapter = adapter

        recyclerFm_recycler.layoutManager = LinearLayoutManager(activity)

        if (adapter.dataCount <= 0) loadData()
    }

    private fun loadData() {
        LoadDataTask(WeakReference(this)).execute("")
    }

    override fun onLoadMore(adapter: AssemblyAdapter) {
        loadData()
    }

    class LoadDataTask(private val fragmentRef: WeakReference<RecyclerLinearLayoutSampleFragment>) : AsyncTask<String, String, List<Any>?>() {

        override fun doInBackground(vararg params: String): List<Any>? {
            val fragment = fragmentRef.get() ?: return null
            fragment.run {
                var index = 0
                val dataList = ArrayList<Any>(size)
                var userStatus = true
                var gameStatus = true
                while (index < size) {
                    if (index % 2 == 0) {
                        val game = Game()
                        game.iconResId = R.mipmap.ic_launcher
                        game.name = "英雄联盟" + (index + nextStart + 1)
                        game.like = if (gameStatus) "不喜欢" else "喜欢"
                        dataList.add(game)
                        gameStatus = !gameStatus
                    } else {
                        val user = User()
                        user.headResId = R.mipmap.ic_launcher
                        user.name = "王大卫" + (index + nextStart + 1)
                        user.sex = if (userStatus) "男" else "女"
                        user.age = "" + (index + nextStart + 1)
                        user.job = "实施工程师"
                        user.monthly = "" + 9000 + index + nextStart + 1
                        dataList.add(user)
                        userStatus = !userStatus
                    }
                    index++
                }
                if (nextStart != 0) {
                    try {
                        Thread.sleep(1000)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
                return dataList
            }
        }

        override fun onPostExecute(objects: List<Any>?) {
            val fragment = fragmentRef.get() ?: return
            fragment.context?.applicationContext ?: return
            fragment.apply {
                nextStart += size
                adapter.addAll(objects)
                adapter.headerItemManager.switchItemEnabled(1)
                adapter.footerItemManager.switchItemEnabled(1)

                val loadMoreEnd = nextStart >= 100
                if (loadMoreEnd) {
                    adapter.headerItemManager.setItemEnabled(0, false)
                    adapter.footerItemManager.setItemEnabled(0, false)
                }
                adapter.moreItemHolder?.isEnabled = !loadMoreEnd
            }
        }
    }
}