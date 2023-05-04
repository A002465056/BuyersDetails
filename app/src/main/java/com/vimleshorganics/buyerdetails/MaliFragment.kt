package com.vimleshorganics.buyerdetails

import android.content.ContentResolver
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception


class MaliFragment(var pageType: String) : Fragment() {
    private lateinit var contactlist:RecyclerView
    private lateinit var progressBar:ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view: View = inflater.inflate(R.layout.malifragment, container, false)
        progressBar=view.findViewById<ProgressBar>(R.id.progressbar)
        contactlist=view.findViewById<RecyclerView>(R.id.contactlist)
        contactlist.layoutManager=LinearLayoutManager(context)
        progressBar.visibility=View.VISIBLE

     /*   var txtView: TextView = view.findViewById<TextView>(R.id.textView)
        txtView.text = pageType*/
        return view

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        contactlist.adapter=ContactAdapter(fetchContacts())

    }

    private fun fetchContacts(): ArrayList<Contact> {
        val contacts = ArrayList<Contact>()
        val contentResolver: ContentResolver = context?.contentResolver!!

        val cursor: Cursor? = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null, null, null, null
        )

        if (cursor != null && cursor.moveToFirst()) {
            do {
                try {
                    val id =
                        cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID))
                    val name =
                        cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME))




                    if (cursor.getInt(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                        val phoneCursor: Cursor? = contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?",
                            arrayOf(id),
                            null
                        )

                        if (phoneCursor != null && phoneCursor.moveToFirst()) {
                            val phoneNumber = phoneCursor.getString(
                                phoneCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER)
                            )
                            contacts.add(Contact(name, phoneNumber))
                        }
                        phoneCursor?.close()
                    }
                } catch (e: Exception) {

                }
            } while (cursor.moveToNext())
        }
        cursor?.close()

        progressBar.visibility=View.GONE
        // Use the fetched contacts here
        return contacts
    }

    data class Contact(val name: String, val phoneNumber: String)

}