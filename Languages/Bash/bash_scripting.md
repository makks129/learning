
grep			print lines that match pattern https://www.gnu.org/savannah-checkouts/gnu/grep/manual/grep.html
	-i 			ignore case
	-A#			include # lines after (e.g. -A5)
	-B#			include # lines before (e.g. -B5)

wc				word count
	-l			coun lines


sed

t

sort

comm

less

awk
	Examples:
	awk "{print $1}"			prints first column of the output
		-F ":"					separates columns by ":"


xargs
	Examples:
	seq 3 | xargs -I# echo num# 	will print: num1 num2 num3

paste
	Examples:
	seq 3 | paste - - -				will print: 1   2   3
