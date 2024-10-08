#[derive(Debug)]
pub(crate) struct L1963();

pub(crate) trait Solution {
    fn min_swaps(s: String) -> i32;
}
impl Solution for L1963{
     fn min_swaps(s: String) -> i32 {
        let mut stk_size = 0;
        for c in s.chars() {
            match c {
                '[' => {
                    stk_size += 1;
                },
                ']' => {
                    if stk_size > 0 {
                        stk_size -= 1;
                    }
                }
                _ => {
                }
            }
        }
        (stk_size + 1) / 2
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    const test_cases: [(&str, i32); 3] = [
        ("][][", 1),
        ("[[[]]]", 0),
        ("[[][]]", 0), ];
    #[test]
    fn min_swaps() {
        for (s, expected) in test_cases {
            assert_eq!(L1963::min_swaps(s.to_string()), expected);
        }
    }
}