package info.imdang.ui.main.write

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import info.imdang.imdang.core.component.theme.Black
import info.imdang.imdang.core.component.theme.GrayScale100
import info.imdang.imdang.core.component.theme.GrayScale600
import info.imdang.imdang.core.component.theme.GrayScale900
import info.imdang.imdang.core.component.theme.ImdangAppNewTheme
import info.imdang.imdang.core.component.theme.SystemRedLight
import info.imdang.imdang.core.component.theme.White
import info.imdang.imdang.core.component.theme.pretendardFont
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale
import info.imdang.core.component.R as ComponentR

@Composable
internal fun DateSelector(
    modifier: Modifier = Modifier,
    selectedDate: LocalDate = LocalDate.now(),
    isExpanded: Boolean,
    onDateSelected: (LocalDate) -> Unit,
    onExpandToggle: () -> Unit,
) {
    val radius = if (isExpanded) 13.dp else 8.dp

    Box(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(durationMillis = 300)
            )
            .background(White, RoundedCornerShape(radius))
            .border(
                BorderStroke(
                    1.dp,
                    if (isExpanded) Color.Transparent else GrayScale100
                ),
                shape = RoundedCornerShape(radius)
            )
            .clickable { onExpandToggle() }
    ) {
        if (!isExpanded) {
            CollapsedDateSelector(
                selectedDate = selectedDate
            )
        } else {
            ExpandedDateSelector(
                selectedDate = selectedDate,
                onDateSelected = onDateSelected
            )
        }
    }
}

@Composable
private fun CollapsedDateSelector(
    selectedDate: LocalDate
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${selectedDate.year}.${
                selectedDate.monthValue.toString().padStart(2, '0')
            }.${selectedDate.dayOfMonth.toString().padStart(2, '0')}",
            style = MaterialTheme.typography.labelSmall.copy(color = GrayScale900)
        )

        Icon(
            painter = painterResource(ComponentR.drawable.celendar),
            contentDescription = "calendar",
            tint = GrayScale600,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
private fun ExpandedDateSelector(
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit
) {
    var currentMonth by remember(selectedDate) { mutableStateOf(selectedDate) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // 월/년 헤더와 네비게이션
        CalendarHeader(
            currentMonth = currentMonth,
            onHeaderClick = { },
            onPreviousMonth = { currentMonth = currentMonth.minusMonths(1) },
            onNextMonth = { currentMonth = currentMonth.plusMonths(1) },
        )

        // 요일 헤더
        DaysOfWeekHeader()

        // Calendar grid
        CalendarGrid(
            modifier = Modifier.padding(top = 5.dp),
            currentMonth = currentMonth,
            selectedDate = selectedDate,
            onDateSelected = onDateSelected
        )
    }
}

@Composable
private fun CalendarHeader(
    currentMonth: LocalDate,
    onHeaderClick: () -> Unit,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.clickable { onHeaderClick() },
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${
                    currentMonth.month.getDisplayName(
                        TextStyle.FULL,
                        Locale.ENGLISH
                    )
                } ${currentMonth.year}",
                style = MaterialTheme.typography.titleMedium.copy(color = GrayScale900)
            )

            Icon(
                painter = painterResource(ComponentR.drawable.chevron_forward),
                contentDescription = null,
                tint = SystemRedLight,
                modifier = Modifier.width(7.dp)
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(31.dp)
        ) {
            Icon(
                painter = painterResource(ComponentR.drawable.chevron_backward),
                contentDescription = "Previous month",
                tint = SystemRedLight,
                modifier = Modifier.clickable { onPreviousMonth() }
            )

            Icon(
                painter = painterResource(ComponentR.drawable.chevron_forward),
                contentDescription = "Next month",
                tint = SystemRedLight,
                modifier = Modifier.clickable { onNextMonth() }
            )
        }
    }
}

@Composable
private fun DaysOfWeekHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        listOf("SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT").forEach { day ->
            Text(
                text = day,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color(0x4D3C3C43),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,
                    lineHeight = 18.sp,
                ),
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun CalendarGrid(
    modifier: Modifier = Modifier,
    currentMonth: LocalDate,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit
) {
    // 현재 월의 첫 번째 날 계산
    val firstDayOfMonth = currentMonth.withDayOfMonth(1)
    // 첫 번째 날의 요일 계산
    val firstDayOfWeek = firstDayOfMonth.dayOfWeek.value % 7

    // 필요한 주 수 동적 계산
    val daysInMonth = currentMonth.lengthOfMonth()
    val totalCellsNeeded = firstDayOfWeek + daysInMonth
    val weeksNeeded = (totalCellsNeeded + 6) / 7

    val calendarFontStyle = androidx.compose.ui.text.TextStyle(
        fontFamily = pretendardFont,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 25.sp,
        letterSpacing = 0.38.sp
    )

    Column(modifier = modifier) {
        val startDate = firstDayOfMonth.minusDays(firstDayOfWeek.toLong())

        repeat(weeksNeeded) { weekIndex ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // 일주일 7일 반복
                repeat(7) { dayIndex ->
                    val date = startDate.plusDays((weekIndex * 7 + dayIndex).toLong())
                    val isCurrentMonth = date.month == currentMonth.month
                    val isSelected = date == selectedDate

                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clickable(enabled = isCurrentMonth) {
                                if (isCurrentMonth) {
                                    onDateSelected(date)
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        if (isCurrentMonth) {
                            if (isSelected) {
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .background(SystemRedLight, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = date.dayOfMonth.toString(),
                                        style = calendarFontStyle.copy(
                                            color = White,
                                            fontWeight = FontWeight.SemiBold
                                        ),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            } else {
                                Text(
                                    text = date.dayOfMonth.toString(),
                                    style = calendarFontStyle.copy(color = Black),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }

            // 마지막 주가 아니면 간격 추가
            if (weekIndex < weeksNeeded - 1) {
                Spacer(modifier = Modifier.height(6.dp))
            }
        }
    }
}

@Preview
@Composable
private fun DateSelectorPreview() {
    var selectedDate by remember { mutableStateOf(LocalDate.of(2025, 11, 22)) }
    var isExpanded by remember { mutableStateOf(true) }

    ImdangAppNewTheme {
        DateSelector(
            selectedDate = selectedDate,
            isExpanded = isExpanded,
            onDateSelected = { selectedDate = it },
            onExpandToggle = { isExpanded = !isExpanded }
        )
    }
}