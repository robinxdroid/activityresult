# activityresult #
简单的获取activity返回值

## Usage ##

### kotlin ###
```java
            val intent = OtherActivity.getIntent(this@KtMainActivity)

            ActivityResult.with(this@KtMainActivity)
                    .start(intent) { _, _, intent ->
                        intent?.let {
                            val data = intent.getStringExtra("data")
                            tvReturn.text = data
                        }

                    }
```
### java ###
```java
                Intent intent = OtherActivity.getIntent(MainActivity.this);

                ActivityResult.with(MainActivity.this)
                        .start(intent, 100, new Function3<Integer, Integer, Intent, Unit>() {
                            @Override
                            public Unit invoke(Integer requestCode, Integer resultCode, Intent intent) {
                                if (intent != null) {
                                    String data = intent.getStringExtra("data");
                                    tvReturn.setText(data);
                                }

                                return null;
                            }
                        });
```
