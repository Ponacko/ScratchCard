import com.example.scratchcard.PLATFORM_ACTIVATION_FIELD
import com.example.scratchcard.model.ScratchCard
import com.example.scratchcard.model.ScratchCardState
import com.example.scratchcard.network.ApiService
import com.example.scratchcard.network.CodeActivator
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertFailsWith

class CodeActivatorTest {

    private lateinit var apiService: ApiService
    private lateinit var codeActivator: CodeActivator
    private lateinit var scratchCard: ScratchCard

    @Before
    fun setup() {
        apiService = mockk()
        codeActivator = CodeActivator(apiService)
        scratchCard = mockk(relaxed = true)
    }

    @Test
    fun `test activate successfully activates card`() = runTest {
        val code = "sample-code"
        val response = mapOf(PLATFORM_ACTIVATION_FIELD to "277029")
        every { scratchCard.code } returns code
        coEvery { apiService.activateCard(code) } returns response
        every { scratchCard.state } returns ScratchCardState.SCRATCHED

        codeActivator.activate(scratchCard)

        verify { scratchCard.setActivated() }
    }

    @Test
    fun `test activate throws exception if activation value is too low`() = runTest {
        val code = "sample-code"
        val response = mapOf(PLATFORM_ACTIVATION_FIELD to "10")
        every { scratchCard.code } returns code
        coEvery { apiService.activateCard(code) } returns response
        every { scratchCard.state } returns ScratchCardState.SCRATCHED

        assertFailsWith<Exception> {
            codeActivator.activate(scratchCard)
        }
    }

    @Test
    fun `test activate throws exception if card is not scratched`() = runTest {
        every { scratchCard.state } returns ScratchCardState.UNSCRATCHED

        assertFailsWith<Exception> {
            codeActivator.activate(scratchCard)
        }
    }

    @Test
    fun `test activate throws exception if card is already activated`() = runTest {
        every { scratchCard.state } returns ScratchCardState.ACTIVATED

        assertFailsWith<Exception> {
            codeActivator.activate(scratchCard)
        }
    }

    @Test
    fun `test canBeActivated returns true if card is scratched and has a code`() {
        every { scratchCard.state } returns ScratchCardState.SCRATCHED
        every { scratchCard.code } returns "sample-code"

        val result = codeActivator.canBeActivated(scratchCard)

        assertTrue(result)
    }

    @Test
    fun `test canBeActivated returns false if card is not scratched`() {
        every { scratchCard.state } returns ScratchCardState.UNSCRATCHED
        every { scratchCard.code } returns "sample-code"

        val result = codeActivator.canBeActivated(scratchCard)

        assertFalse(result)
    }

    @Test
    fun `test canBeActivated returns false if card has no code`() {
        every { scratchCard.state } returns ScratchCardState.SCRATCHED
        every { scratchCard.code } returns ""

        val result = codeActivator.canBeActivated(scratchCard)

        assertFalse(result)
    }

    @Test
    fun `test isAlreadyActivated returns true if card is activated`() {
        every { scratchCard.state } returns ScratchCardState.ACTIVATED

        val result = codeActivator.isAlreadyActivated(scratchCard)

        assertTrue(result)
    }

    @Test
    fun `test isAlreadyActivated returns false if card is not activated`() {
        every { scratchCard.state } returns ScratchCardState.SCRATCHED

        val result = codeActivator.isAlreadyActivated(scratchCard)

        assertFalse(result)
    }
}
