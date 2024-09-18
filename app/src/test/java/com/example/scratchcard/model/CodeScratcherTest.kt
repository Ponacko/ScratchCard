import com.example.scratchcard.model.CodeScratcher
import com.example.scratchcard.model.ScratchCard
import com.example.scratchcard.model.ScratchCardState
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.util.UUID
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CodeScratcherTest {

    private lateinit var codeScratcher: CodeScratcher
    private lateinit var scratchCard: ScratchCard

    @Before
    fun setup() {
        codeScratcher = CodeScratcher()
        scratchCard = mockk(relaxed = true)
    }

    @Test
    fun `test scratch sets scratched code on card`() = runTest {
        val mockUUID = "mocked-uuid"
        mockkStatic(UUID::class)
        every { UUID.randomUUID().toString() } returns mockUUID

        codeScratcher.scratch(scratchCard)

        coVerify { scratchCard.setScratched(mockUUID) }

        unmockkStatic(UUID::class)
    }

    @Test
    fun `test canBeScratched returns true if card is unscratched`() {
        every { scratchCard.state } returns ScratchCardState.UNSCRATCHED

        val result = codeScratcher.canBeScratched(scratchCard)

        assertTrue(result)
    }

    @Test
    fun `test canBeScratched returns false if card is already scratched`() {
        every { scratchCard.state } returns ScratchCardState.SCRATCHED

        val result = codeScratcher.canBeScratched(scratchCard)

        assertFalse(result)
    }
}
