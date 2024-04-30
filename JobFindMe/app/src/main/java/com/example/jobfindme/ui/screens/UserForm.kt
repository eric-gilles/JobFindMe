import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Constraints.Companion.Infinity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.jobfindme.Shape

@Composable
fun UserForm(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 375.dp)
            .requiredHeight(height = 812.dp)
    ) {
        Box(
            modifier = Modifier
                .requiredWidth(width = 375.dp)
                .requiredHeight(height = 812.dp)
                .background(color = Color(0xfff6f6f6))
        ) {
            Shape(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = (-99).dp,
                        y = (-109).dp))

            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 84.dp,
                        y = 607.dp)
                    .requiredWidth(width = 207.dp)
                    .requiredHeight(height = 34.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 207.dp)
                        .requiredHeight(height = 34.dp)
                        .clip(shape = RoundedCornerShape(3.dp))
                        .background(color = Color(0xff50c2c9))) {
                    Text(
                        lineHeight = 9.sp,
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.White,
                                    fontSize = 16.sp
                                )
                            ) { append("Upload a CV") }
                            withStyle(
                                style = SpanStyle(
                                    color = Color(0xffdf0e0e),
                                    fontSize = 16.sp
                                )
                            ) { append("*") }
                        },
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 52.dp,
                                y = 5.1817626953125.dp
                            )
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 64.dp,
                    y = 83.dp)
                .requiredWidth(width = 246.dp)
                .requiredHeight(height = 69.dp)
        ) {
            Text(
                textAlign = TextAlign.Center,
                lineHeight = Infinity.sp,
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(
                        color = Color.Black,
                        fontSize = 13.168039321899414.sp,
                        fontStyle = FontStyle.Italic)) {append("We'll help you find a job. ")}
                    withStyle(style = SpanStyle(
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold)) {append("Please Register")}},
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(x = 0.5.dp,
                        y = 38.dp)
                    .requiredWidth(width = 187.dp)
                    .requiredHeight(height = 31.dp))
            Text(
                text = "Welcome Candidat !",
                color = Color.Black.copy(alpha = 0.75f),
                textAlign = TextAlign.Center,
                lineHeight = 6.25.em,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(x = 0.dp,
                        y = 0.dp)
                    .requiredWidth(width = 246.dp)
                    .requiredHeight(height = 28.dp))
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 37.dp,
                    y = 165.4990234375.dp)
                .requiredWidth(width = 298.dp)
                .requiredHeight(height = 436.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 2.dp,
                        y = 98.9478759765625.dp)
                    .requiredWidth(width = 296.dp)
                    .requiredHeight(height = 39.dp)
                    .clip(shape = RoundedCornerShape(15.705995559692383.dp))
                    .background(color = Color.White))
            {
                Text(
                    lineHeight = 9.sp,
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(
                            color = Color.Black,
                            fontSize = 16.sp)) {append("Enter your lastname")}
                        withStyle(style = SpanStyle(
                            color = Color(0xffdf0e0e))) {append(" *")}},
                    modifier = Modifier.align(Alignment.Center))

            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 2.dp,
                        y = 49.473876953125.dp)
                    .requiredWidth(width = 296.dp)
                    .requiredHeight(height = 39.dp)
                    .clip(shape = RoundedCornerShape(15.705995559692383.dp))
                    .background(color = Color.White)){
                Text(
                    lineHeight = 9.sp,
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(
                            color = Color.Black,
                            fontSize = 16.sp)) {append("Enter your firstname")}
                        withStyle(style = SpanStyle(
                            color = Color(0xffdf0e0e))) {append(" *")}},
                    modifier = Modifier.align(Alignment.Center))
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp,
                        y = 146.85107421875.dp)
                    .requiredWidth(width = 296.dp)
                    .requiredHeight(height = 39.dp)
                    .clip(shape = RoundedCornerShape(15.705995559692383.dp))
                    .background(color = Color.White)){
                Text(
                    text = "Enter your nationality",
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.Center))
            }

            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 2.dp,
                        y = 0.dp)
                    .requiredWidth(width = 296.dp)
                    .requiredHeight(height = 39.dp)
                    .clip(shape = RoundedCornerShape(15.705995559692383.dp))
                    .background(color = Color.White)){
                Text(
                    text = "Enter your email",
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.Center))
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp,
                        y = 194.75439453125.dp)
                    .requiredWidth(width = 296.dp)
                    .requiredHeight(height = 39.dp)
                    .clip(shape = RoundedCornerShape(15.705995559692383.dp))
                    .background(color = Color.White)){
                Text(
                    text = "Enter your phone number",
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.Center))
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp,
                        y = 242.6575927734375.dp)
                    .requiredWidth(width = 296.dp)
                    .requiredHeight(height = 39.dp)
                    .clip(shape = RoundedCornerShape(15.705995559692383.dp))
                    .background(color = Color.White)){
                Text(
                    text = "Enter your birthdate",
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.Center))
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp,
                        y = 292.5009765625.dp)
                    .requiredWidth(width = 296.dp)
                    .requiredHeight(height = 39.dp)
                    .clip(shape = RoundedCornerShape(15.705995559692383.dp))
                    .background(color = Color.White)){
                Text(
                    text = "Enter your city",
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.Center))
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp,
                        y = 345.5009765625.dp)
                    .requiredWidth(width = 296.dp)
                    .requiredHeight(height = 39.dp)
                    .clip(shape = RoundedCornerShape(15.705995559692383.dp))
                    .background(color = Color.White)){
                Text(
                    lineHeight = 9.sp,
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(
                            color = Color.Black,
                            fontSize = 16.sp)) {append("Enter your password")}
                        withStyle(style = SpanStyle(
                            color = Color(0xffdf0e0e))) {append(" *")}},
                    modifier = Modifier.align(Alignment.Center))
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp,
                        y = 396.5009765625.dp)
                    .requiredWidth(width = 296.dp)
                    .requiredHeight(height = 39.dp)
                    .clip(shape = RoundedCornerShape(15.705995559692383.dp))
                    .background(color = Color.White)){
                Text(
                    lineHeight = 9.sp,
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(
                            color = Color.Black,
                            fontSize = 16.sp)) {append("Confirm your password")}
                        withStyle(style = SpanStyle(
                            color = Color(0xffdf0e0e))) {append(" *")}},
                    modifier = Modifier.align(Alignment.Center))
            }
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 24.dp,
                    y = 652.dp)
                .requiredWidth(width = 326.dp)
                .requiredHeight(height = 64.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 326.dp)
                    .requiredHeight(height = 64.dp)
                    .clip(shape = RoundedCornerShape(6.dp))
                    .background(color = Color(0xff50c2c9))) {
                Text(
                    text = "Register",
                    color = Color.White,
                    lineHeight = 6.25.em,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(
                            x = 0.dp,
                            y = 14.272705078125.dp
                        )
                )
            }
        }
        Text(
            lineHeight = 9.sp,
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(
                    color = Color(0xff1e1e1e),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium)) {append("Already have an account ? ")}
                withStyle(style = SpanStyle(
                    color = Color(0xff50c2c9),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium)) {append("Sign In")}},
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(x = (-0.5).dp,
                    y = 732.dp))
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 84.dp,
                    y = 761.dp)
                .requiredWidth(width = 207.dp)
                .requiredHeight(height = 34.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 207.dp)
                    .requiredHeight(height = 34.dp)
                    .clip(shape = RoundedCornerShape(3.dp))
                    .background(color = Color(0xff50c2c9)))
            Text(
                text = "Anonymous ?",
                color = Color.White,
                lineHeight = 9.38.em,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 52.dp,
                        y = 3.1817626953125.dp))
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 38.dp,
                    y = 57.dp)
                .requiredWidth(width = 32.dp)
                .requiredHeight(height = 29.dp)
                .clip(shape = MaterialTheme.shapes.small)
                .background(color = Color.White)
                .border(border = BorderStroke(8.dp, Color.White),
                    shape = MaterialTheme.shapes.small))
    }
}


