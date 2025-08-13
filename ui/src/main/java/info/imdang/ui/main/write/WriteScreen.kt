package info.imdang.ui.main.write

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import info.imdang.imdang.core.component.buttons.ButtonSize
import info.imdang.imdang.core.component.buttons.GhostButton
import info.imdang.imdang.core.component.chip.CustomMaterialChip
import info.imdang.imdang.core.component.theme.Gray900
import info.imdang.imdang.core.component.theme.GrayScale100
import info.imdang.imdang.core.component.theme.GrayScale200
import info.imdang.imdang.core.component.theme.GrayScale400
import info.imdang.imdang.core.component.theme.GrayScale600
import info.imdang.imdang.core.component.theme.GrayScale900
import info.imdang.imdang.core.component.theme.ImdangAppNewTheme
import info.imdang.imdang.core.component.theme.ImdangPreview
import info.imdang.imdang.core.component.theme.Orange500
import info.imdang.imdang.core.component.theme.White
import info.imdang.ui.R
import info.imdang.ui.enums.SeoulArea
import java.time.LocalDate
import info.imdang.core.component.R as ComponentR

sealed interface SheetMode {
    data object DistrictSelect : SheetMode
    data class AddressSearch(val slotIndex: Int) : SheetMode
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WriteRoute(onBackClick: () -> Unit) {
    val isSubmitEnabled by remember { mutableStateOf(true) }
    var selectedDistrict by remember { mutableStateOf<SeoulArea?>(null) }
    val slots = remember { mutableStateListOf<String?>(null) }
    val maxSlots = 3

    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var isDateSelectorExpanded by remember { mutableStateOf(false) }

    var isSheetVisible by remember { mutableStateOf(false) }
    var sheetMode by remember { mutableStateOf<SheetMode>(SheetMode.DistrictSelect) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)

    LaunchedEffect(sheetMode) {
        when (sheetMode) {
            is SheetMode.DistrictSelect -> sheetState.partialExpand()
            is SheetMode.AddressSearch -> sheetState.expand()
        }
    }

    WriteScreen(
        onClickedCancel = onBackClick,
        onClickedSubmit = {},
        onClickedDistrict = {
            sheetMode = SheetMode.DistrictSelect
            isSheetVisible = true
        },
        onClickSlot = { index ->
            sheetMode = SheetMode.AddressSearch(slotIndex = index)
            isSheetVisible = true
        },
        onRemoveSlot = { index ->
            if (index in slots.indices) {
                slots.removeAt(index)
                if (slots.isEmpty()) slots.add(null)
            }
        },
        onClickAddSlot = {
            if (slots.size < maxSlots && slots.lastOrNull() != null) {
                slots.add(null)
            }
        },
        isSubmitEnabled = isSubmitEnabled,
        selectedDistrict = selectedDistrict,
        slots = slots,
        selectedDate = selectedDate,
        isDateSelectorExpanded = isDateSelectorExpanded,
        onDateSelected = { selectedDate = it },
        onDateSelectorToggle = { isDateSelectorExpanded = !isDateSelectorExpanded },
    )

    if (isSheetVisible) {
        ModalBottomSheet(
            onDismissRequest = { isSheetVisible = false },
            sheetState = sheetState
        ) {
            when (sheetMode) {
                SheetMode.DistrictSelect -> {
                    DistrictSelectSheetContent(
                        selected = selectedDistrict,
                        onSelect = {
                            selectedDistrict = it
                            isSheetVisible = false
                        }
                    )
                }

                is SheetMode.AddressSearch -> {
                    AddressSearchSheetContent(
                        onAddressSelected = {
                            Log.d("KakaoAddressSearch", "Selected address: $it")
                        },
                        onDismiss = {
                            isSheetVisible = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
internal fun WriteScreen(
    onClickedCancel: () -> Unit,
    onClickedSubmit: () -> Unit,
    onClickedDistrict: () -> Unit,
    onClickSlot: (Int) -> Unit,
    onRemoveSlot: (Int) -> Unit,
    onClickAddSlot: () -> Unit,
    isSubmitEnabled: Boolean,
    selectedDistrict: SeoulArea?,
    slots: List<String?>,
    selectedDate: LocalDate,
    isDateSelectorExpanded: Boolean,
    onDateSelected: (LocalDate) -> Unit,
    onDateSelectorToggle: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp),
    ) {
        TopArea(
            onClickedCancel = onClickedCancel,
            onClickedSubmit = onClickedSubmit,
            isSubmitEnabled = isSubmitEnabled,
        )

        InterestDistrict(
            onClickedDistrict = onClickedDistrict,
            selectedDistrict = selectedDistrict
        )

        ApartmentArea(
            slots = slots,
            onClickSlot = onClickSlot,
            onRemoveSlot = onRemoveSlot,
            onClickAddSlot = onClickAddSlot
        )

        VisitDate(
            selectedDate = selectedDate,
            isExpanded = isDateSelectorExpanded,
            onDateSelected = onDateSelected,
            onExpandToggle = onDateSelectorToggle
        )
    }
}

@Composable
private fun TopArea(
    onClickedCancel: () -> Unit,
    onClickedSubmit: () -> Unit,
    isSubmitEnabled: Boolean,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier
                .clickable { onClickedCancel() },
            text = stringResource(R.string.btn_cancel),
            style = MaterialTheme.typography.titleMedium.copy(Gray900)
        )

        Text(
            modifier = Modifier
                .clickable(enabled = isSubmitEnabled) { onClickedSubmit() },
            text = stringResource(R.string.btn_submit),
            style = MaterialTheme.typography.titleMedium.copy(
                color = if (isSubmitEnabled) Orange500 else Gray900
            )
        )
    }
}

@Composable
private fun InterestDistrict(
    onClickedDistrict: () -> Unit,
    selectedDistrict: SeoulArea?,
) {
    Row(
        modifier = Modifier
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = stringResource(R.string.interest_district),
            style = MaterialTheme.typography.titleMedium.copy(GrayScale900)
        )
        Text(
            text = "*",
            style = MaterialTheme.typography.titleSmall.copy(Orange500)
        )
    }

    CustomMaterialChip(
        modifier = Modifier
            .padding(top = 12.dp),
        text = stringResource(selectedDistrict?.resId ?: R.string.district),
        isSelected = false,
        iconResId = ComponentR.drawable.down,
        onClick = onClickedDistrict,
    )
}

@Composable
private fun ApartmentArea(
    slots: List<String?>,
    onClickSlot: (Int) -> Unit,
    onRemoveSlot: (Int) -> Unit,
    onClickAddSlot: () -> Unit,
) {
    Text(
        modifier = Modifier.padding(top = 20.dp),
        text = stringResource(R.string.apartment),
        style = MaterialTheme.typography.titleSmall.copy(GrayScale900)
    )

    Spacer(Modifier.height(12.dp))

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        slots.forEachIndexed { index, value ->
            ApartmentSlot(
                text = value,
                onClickSlot = { onClickSlot(index) },
                onRemoveSlot = { onRemoveSlot(index) }
            )
        }
    }

    Button(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
            .height(42.dp),
        onClick = onClickAddSlot,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = GrayScale100
        )
    ) {
        Text(
            text = stringResource(R.string.writing_add),
            style = MaterialTheme.typography.titleSmall.copy(GrayScale900)
        )
    }
}

@Composable
private fun ApartmentSlot(
    text: String?,
    onClickSlot: () -> Unit,
    onRemoveSlot: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 52.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(BorderStroke(1.dp, GrayScale200))
            .clickable { onClickSlot() }
            .padding(horizontal = 16.dp),
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
                .padding(top = 17.dp, bottom = 17.dp, end = 32.dp),
            text = text ?: stringResource(R.string.search_apartment_complex_name),
            style = MaterialTheme.typography.labelSmall.copy(
                if (text == null) GrayScale400 else GrayScale900
            ),
        )

        if (text != null) {
            GhostButton(
                modifier = Modifier
                    .padding(top = 3.dp),
                onClick = onRemoveSlot,
                buttonSize = ButtonSize.S,
                text = stringResource(R.string.btn_delete),
            )
        }
    }
}

@Composable
private fun VisitDate(
    selectedDate: LocalDate,
    isExpanded: Boolean,
    onDateSelected: (LocalDate) -> Unit,
    onExpandToggle: () -> Unit,
) {
    Text(
        modifier = Modifier.padding(top = 20.dp),
        text = stringResource(R.string.visit_date),
        style = MaterialTheme.typography.titleMedium.copy(GrayScale900)
    )

    DateSelector(
        modifier = Modifier.padding(top = 12.dp),
        selectedDate = selectedDate,
        isExpanded = isExpanded,
        onDateSelected = onDateSelected,
        onExpandToggle = onExpandToggle
    )
}

@Composable
private fun DistrictSelectSheetContent(
    selected: SeoulArea?,
    onSelect: (SeoulArea) -> Unit,
) {
    val districts = SeoulArea.entries.toList()

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxWidth()
            .background(White),
        contentPadding = PaddingValues(top = 32.dp, bottom = 40.dp, start = 20.dp, end = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(
            items = districts,
            key = { district -> district.name }
        ) { district ->
            val isSelected = district == selected
            Box(
                modifier = Modifier
                    .height(44.dp)
                    .background(
                        color = if (isSelected) Orange500 else White,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = if (isSelected) Color.Transparent else GrayScale200,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .clickable { onSelect(district) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = district.resId),
                    color = if (isSelected) White else GrayScale600,
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        }
    }
}

@Composable
private fun AddressSearchSheetContent(
    onAddressSelected: (String) -> Unit,
    onDismiss: () -> Unit,
) {
    KakaoAddressSearchWebView(
        onAddressSelected = { result ->
            val addressText = if (!result.isEmpty) {
                result.fullAddress
            } else {
                "주소 정보 없음"
            }
            onAddressSelected(addressText)
        },
        onCloseCallback = {
            onDismiss()
        }
    )
}

@ImdangPreview
@Composable
private fun WriteScreenPreview() {
    ImdangAppNewTheme {
        val slots = remember { mutableStateListOf<String?>(null) }
        var selectedDate by remember { mutableStateOf(LocalDate.now()) }
        var isDateSelectorExpanded by remember { mutableStateOf(false) }

        WriteScreen(
            onClickedCancel = {},
            onClickedSubmit = {},
            onClickedDistrict = {},
            onClickSlot = {},
            onRemoveSlot = {},
            onClickAddSlot = {},
            isSubmitEnabled = true,
            selectedDistrict = null,
            slots = slots,
            selectedDate = selectedDate,
            isDateSelectorExpanded = isDateSelectorExpanded,
            onDateSelected = { selectedDate = it },
            onDateSelectorToggle = { isDateSelectorExpanded = !isDateSelectorExpanded }
        )
    }
}

@ImdangPreview
@Composable
private fun DistrictSelectSheetContentPreview() {
    ImdangAppNewTheme {
        DistrictSelectSheetContent(
            selected = SeoulArea.GANGNAM,
            onSelect = {}
        )
    }
}

@ImdangPreview
@Composable
private fun AddressSearchSheetContentPreview() {
    ImdangAppNewTheme {
        AddressSearchSheetContent(
            onAddressSelected = {},
            onDismiss = {},
        )
    }
}