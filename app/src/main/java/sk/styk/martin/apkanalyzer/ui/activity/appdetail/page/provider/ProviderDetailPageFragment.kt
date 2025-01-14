package sk.styk.martin.apkanalyzer.ui.activity.appdetail.page.provider

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_app_detail_provider.*
import sk.styk.martin.apkanalyzer.R
import sk.styk.martin.apkanalyzer.model.detail.ContentProviderData
import sk.styk.martin.apkanalyzer.ui.activity.appdetail.pager.AppDetailPagerContract

/**
 * @author Martin Styk
 * @version 30.06.2017.
 */
class ProviderDetailPageFragment : Fragment(), ProviderDetailPageContract.View {

    private lateinit var presenter: ProviderDetailPageContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = ProviderDetailPagePresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): android.view.View? {
        return inflater.inflate(R.layout.fragment_app_detail_provider, container, false)
    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.initialize(arguments?.getString(AppDetailPagerContract.ARG_PACKAGE_NAME) ?: throw IllegalArgumentException("data null"))
        presenter.view = this
        presenter.getData()
    }

    override fun showData() {
        recycler_view_provider.adapter = ProviderDetailListAdapter(presenter)
        recycler_view_provider.setHasFixedSize(true)
    }

}
