import com.example.scratchcard.model.ScratchCard
import com.example.scratchcard.network.CodeActivator
import com.example.scratchcard.viewmodel.ActivationViewModel
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class ActivationViewModelTest {

    private lateinit var scratchCard: ScratchCard
    private lateinit var codeActivator: CodeActivator
    private lateinit var viewModel: ActivationViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        scratchCard = mockk()
        codeActivator = mockk()
        viewModel = ActivationViewModel(scratchCard, codeActivator)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test activate sets activationError on exception`() = runTest {
        val errorMessage = "Activation failed"
        coEvery { codeActivator.activate(scratchCard) } throws Exception(errorMessage)
        every { codeActivator.canBeActivated(scratchCard) } returns true
        every { codeActivator.isAlreadyActivated(scratchCard) } returns false

        viewModel.activate()

        advanceUntilIdle()
        assertEquals("An error occurred: $errorMessage", viewModel.activationError)
    }

    @Test
    fun `test activate clears activationError after success`() = runTest {
        coEvery { codeActivator.activate(scratchCard) } just Runs
        every { codeActivator.canBeActivated(scratchCard) } returns true
        every { codeActivator.isAlreadyActivated(scratchCard) } returns false

        viewModel.activate()
        advanceUntilIdle()
        viewModel.clearError()

        assertNull(viewModel.activationError)
    }

    @Test
    fun `test canBeActivated reflects correct value from codeActivator`() {
        every { codeActivator.canBeActivated(scratchCard) } returns true

        val result = viewModel.canBeActivated

        assertTrue(result)
    }

    @Test
    fun `test isAlreadyActivated reflects correct value from codeActivator`() {
        every { codeActivator.isAlreadyActivated(scratchCard) } returns true

        val result = viewModel.isAlreadyActivated

        assertTrue(result)
    }
}
