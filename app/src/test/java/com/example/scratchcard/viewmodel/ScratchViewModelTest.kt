import com.example.scratchcard.model.CodeScratcher
import com.example.scratchcard.model.ScratchCard
import com.example.scratchcard.viewmodel.ScratchViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class ScratchViewModelTest {

    private lateinit var scratchCard: ScratchCard
    private lateinit var codeScratcher: CodeScratcher
    private lateinit var viewModel: ScratchViewModel

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        scratchCard = mockk()
        codeScratcher = mockk()
        viewModel = ScratchViewModel(scratchCard, codeScratcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test cancelScratching cancels the job`() = testScope.runTest {
        every { codeScratcher.canBeScratched(scratchCard) } returns true
        viewModel.scratch()

        viewModel.cancelScratching()

        assertFalse(viewModel.isScratching)
    }

    @Test
    fun `test canBeScratched reflects correct value from codeScratcher`() {
        every { codeScratcher.canBeScratched(scratchCard) } returns true

        val result = viewModel.canBeScratched

        assertTrue(result)
    }
}
