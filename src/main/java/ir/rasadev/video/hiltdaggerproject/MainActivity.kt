package ir.rasadev.video.hiltdaggerproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ir.rasadev.video.hiltdaggerproject.databinding.ActivityMainBinding
import ir.rasadev.video.hiltdaggerproject.room.NoteDatabase
import ir.rasadev.video.hiltdaggerproject.room.NoteModel
import ir.rasadev.video.hiltdaggerproject.room.Repository
import ir.rasadev.video.hiltdaggerproject.view.NoteAdapter
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var repository: Repository

    @Inject
    lateinit var noteAdapter: NoteAdapter

    @Inject
    lateinit var model: NoteModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnSave.setOnClickListener(View.OnClickListener {
                model.id = 0
                model.title = edText.text.toString()
                repository.saveNote(model)
                edText.setText("")
                noteAdapter.differ.submitList(repository.getAllNote())
            })
            noteAdapter.differ.submitList(repository.getAllNote())
            recyclerview.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = noteAdapter
            }

            //click item on recyclerview that defines in NoteAdapter
            noteAdapter.setOnItemClickListener {
                Toast.makeText(this@MainActivity,"${it.title}",Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}