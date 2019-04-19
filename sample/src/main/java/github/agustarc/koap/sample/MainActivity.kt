package github.agustarc.koap.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        write.setOnClickListener {
            val user = User(id = Random.nextInt(5000), name = "Leopold")

            AccountPreference.age = Random.nextInt(100)
            AccountPreference.name = "Name ".plus(Random.nextInt(100))
            AccountPreference.hireable = Random.nextBoolean()
            AccountPreference.score = Random.nextFloat()
            AccountPreference.repos = Random.nextLong(5000)
            AccountPreference.followers = arrayListOf(user, user)
            AccountPreference.friend = user
        }

        read.setOnClickListener { result.text = print() }

        result.text = print()
    }

    private fun print(): String = buildString {
        append("{ age : ${AccountPreference.age}, name : ${AccountPreference.name} } ")
        append("\n")
        append("{ hireable : ${AccountPreference.hireable}, score : ${AccountPreference.score}, repos : ${AccountPreference.repos} }")
        append("\n")
        append("{ followers : ${AccountPreference.followers}, friend : ${AccountPreference.friend} }")
    }
}
