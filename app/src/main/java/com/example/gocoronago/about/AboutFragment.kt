package com.example.gocoronago.about

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.gocoronago.MainActivity
import com.example.gocoronago.R
import kotlinx.android.synthetic.main.about_fragment.*

/**
 * Created by haqiqiw on 07/10/20.
 */
class AboutFragment : Fragment() {

    private val contributors = listOf(
        Contributor("Ritwik Shanker", "🇮🇳", true),
        Contributor("Sunny", "🇮🇳"),
        Contributor("M. Asrof Bayhaqqi", "🇮🇩"),
        Contributor("Jacob", "\uD83C\uDDF7\uD83C\uDDFA"),
        Contributor("Matthew Scibilia", "\uD83C\uDDE6\uD83C\uDDFA")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.about_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavBar()
        renderVersion()
        renderContributors()
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
            val pInfo = requireContext().packageManager.getPackageInfo(
                requireContext().packageName, 0
            )
            val version = pInfo?.versionName
            textVersion.text = version
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
    }

    private fun renderContributors() {
        containerContributors?.apply {
            removeAllViews()
            contributors.mapIndexed { index, item ->
                addView(
                    createContributorTextView(index, item),
                    LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
                )
            }
        }
    }

    private fun createContributorTextView(index: Int, contributor: Contributor): TextView {
        val contributorText = "${contributor.flag} ${contributor.name}" +
                if (contributor.owner) " (Owner)" else ""
        return TextView(requireContext()).apply {
            text = contributorText
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
            setPadding(0, if (index == 0) 0 else 4, 0, 0)
        }
    }

    data class Contributor(val name: String, val flag: String, val owner: Boolean = false)

    companion object {
        fun newInstance() = AboutFragment()
    }
}
