package mumtaz.binar.studikasussatu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var mdbNew : StudentDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mdbNew = StudentDatabase.getInstance(this)

        getDataStudent()

        fab_add.setOnClickListener {
            startActivity(Intent(this, AddStudentActivity::class.java))
        }
    }

    private fun getDataStudent () {
        rv_student.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        GlobalScope.launch {
            val listdata = mdbNew?.studentDao()?.getAllStudent()

            runOnUiThread {
                listdata.let {
                    val adapt = AdapterStudent(it!!){
                        val detail = bundleOf("detail" to it)
                        startActivity(Intent(this@MainActivity, DetailActivity::class.java), detail)
                    }
                    rv_student.adapter = adapt
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getDataStudent()
    }

    override fun onDestroy() {
        super.onDestroy()
        StudentDatabase.destroyInstance()
    }
}