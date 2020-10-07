package com.example.gocoronago.about

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gocoronago.MainActivity
import com.example.gocoronago.R
import kotlinx.android.synthetic.main.about_fragment.*

/**
 * Created by haqiqiw on 07/10/20.
 */
class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.about_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavBar()
        renderVersion()
    }

    private fun setupNavBar() {
        (activity as? MainActivity)?.apply {
            title = getString(R.string.about_us)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeButtonEnabled(true)
        }
        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.about_us)?.isVisible = false
        super.onPrepareOptionsMenu(menu)
    }

    private fun renderVersion() {
        try {
            val pInfo: PackageInfo = requireContext().packageManager.getPackageInfo(requireContext().packageName, 0)
            val version = pInfo.versionName
            textVersion.text = version
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
    }

    companion object {
        fun newInstance() = AboutFragment()
    }
}
