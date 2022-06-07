@file:Suppress("MemberVisibilityCanBePrivate")

package mumtaz.binar.studikasussatu

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_adapter_student.view.*

class AdapterStudent (val listStudent : List<Student>, private val onClick : (Student) -> Unit) : RecyclerView.Adapter<AdapterStudent.ViewHolder>(){

    private var mdb : StudentDatabase? = null
    class ViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent.context).inflate(R.layout.item_adapter_student, parent, false)
        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tv_id_student.text = listStudent[position].id.toString()
        holder.itemView.tv_nama_student.text = listStudent[position].nama
        holder.itemView.tv_email_student.text = listStudent[position].email

        holder.itemView.cardItem.setOnClickListener {
            onClick(listStudent[position])
        }
        holder.itemView.btn_delete.setOnClickListener {
            mdb = StudentDatabase.getInstance(it.context)

            AlertDialog.Builder(it.context)
                .setTitle("Hapus Data")
                .setMessage("Yakin Hapus Data ?")
                .setPositiveButton("Ya"){ dialogInterface: DialogInterface, i: Int ->

                }
                .setNegativeButton("Tidak"){ dialogInterface: DialogInterface, i: Int ->
                    dialogInterface.dismiss()
                }
                .show()
        }



    }

    override fun getItemCount(): Int {
        return listStudent.size
    }
}