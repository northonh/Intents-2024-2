package br.edu.ifsp.scl.ads.pdm.intents

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.scl.ads.pdm.intents.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    companion object Constantes {
        const val PARAMETRO_EXTRA = "PARAMETRO_EXTRA"
        const val PARAMETRO_REQUEST_CODE = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        setSupportActionBar(amb.toolbarTb)
        supportActionBar?.apply {
            title = getString(R.string.app_name)
            subtitle = this@MainActivity.javaClass.simpleName
        }

        amb.entrarParametroBt.setOnClickListener {
//            val parametroIntent = Intent(this, ParametroActivity::class.java)
//            parametroIntent.putExtra(PARAMETRO_EXTRA, amb.parametroTv.text.toString())
//            startActivityForResult(parametroIntent, PARAMETRO_REQUEST_CODE)

            Intent(this, ParametroActivity::class.java).apply {
                amb.parametroTv.text.toString().let{
                    putExtra(PARAMETRO_EXTRA, it)
                }
                startActivityForResult(this, PARAMETRO_REQUEST_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PARAMETRO_REQUEST_CODE && resultCode == RESULT_OK) {
            data?.getStringExtra(PARAMETRO_EXTRA)?.let{ retorno ->
                amb.parametroTv.text = retorno
            }
        }
    }
}