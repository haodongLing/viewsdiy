package com.haodong.pracmodule.recyclerviewstudy.itemdivider;

/**
 * created by linghaoDo on 2019/8/6
 * <p>
 * description:
 */
public class Divider {
    private SideLine leftSideLine;
    private SideLine rightSideLine;
    private SideLine TopSideLine;
    private SideLine BottomSideLine;

    public Divider(SideLine leftSideLine, SideLine rightSideLine, SideLine topSideLine, SideLine bottomSideLine) {
        this.leftSideLine = leftSideLine;
        this.rightSideLine = rightSideLine;
        TopSideLine = topSideLine;
        BottomSideLine = bottomSideLine;
    }

    public SideLine getLeftSideLine() {
        return leftSideLine;
    }

    public void setLeftSideLine(SideLine leftSideLine) {
        this.leftSideLine = leftSideLine;
    }

    public SideLine getRightSideLine() {
        return rightSideLine;
    }

    public void setRightSideLine(SideLine rightSideLine) {
        this.rightSideLine = rightSideLine;
    }

    public SideLine getTopSideLine() {
        return TopSideLine;
    }

    public void setTopSideLine(SideLine topSideLine) {
        TopSideLine = topSideLine;
    }

    public SideLine getBottomSideLine() {
        return BottomSideLine;
    }

    public void setBottomSideLine(SideLine bottomSideLine) {
        BottomSideLine = bottomSideLine;
    }

    public static class Builder {
        private SideLine leftSideLine;
        private SideLine rightSideLine;
        private SideLine topSideLine;
        private SideLine bottomSideLine;

        public Builder setLeftSideLine(SideLine leftSideLine) {
            this.leftSideLine = leftSideLine;
            return this;
        }

        public Builder setRightSideLine(SideLine rightSideLine) {
            this.rightSideLine = rightSideLine;
            return this;
        }

        public Builder setTopSideLine(SideLine topSideLine) {
            topSideLine = topSideLine;
            return this;
        }

        public Builder setBottomSideLine(SideLine bottomSideLine) {
            bottomSideLine = bottomSideLine;
            return this;
        }

        public Divider create() {
            SideLine defaultSideLine = new SideLine(false, 0xff666666, 0, 0, 0);
            leftSideLine = (leftSideLine != null ? leftSideLine : defaultSideLine);
            topSideLine = (topSideLine != null ? topSideLine : defaultSideLine);
            rightSideLine = (rightSideLine != null ? rightSideLine : defaultSideLine);
            bottomSideLine = (bottomSideLine != null ? bottomSideLine : defaultSideLine);
            return new Divider(this.leftSideLine, this.rightSideLine, this.topSideLine, this.bottomSideLine);
        }

    }
}
